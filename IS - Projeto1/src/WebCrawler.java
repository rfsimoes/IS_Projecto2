import generated.Cnn;
import generated.Date;
import generated.News;
import generated.Region;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import javax.jms.JMSException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawler {

	// Criar objecto Cnn
	static Cnn cnn = new Cnn();
	// Criar array de noticias auxiliar
	static ArrayList<News> noticias;
	// Criar array de regi�es auxiliar
	static ArrayList<Region> regioes = new ArrayList<Region>();

	public static void main(String[] args) throws InterruptedException {

		// Tentar publicar logs
		//retryToPublish();

		// Fazer parsing do site da CNN
		collectNews();

		// Extrair a informa��o para um documento XML
		javaToXML();
	}

	
	/*
	 * Fun��o para tentar publicar logs, caso existam em disco
	 */
	private static void retryToPublish() throws InterruptedException {
		File folder = new File("logs");
		File[] listOfFiles = folder.listFiles();
		String xmlString;
		long beginTime = System.currentTimeMillis();

		while (listOfFiles.length > 0) {
			if (listOfFiles[0].isFile()) {
				System.out.println("Publishing file " + listOfFiles[0].getName());
				xmlString = readFile(listOfFiles[0]);
				while (true) {
					try {
						Sender s = new Sender();
						s.send(xmlString);
						s.close();
						listOfFiles[0].delete();
						listOfFiles = folder.listFiles();
						System.out.println("Published");
						break;
					} catch (JMSException e) {
						//e.printStackTrace();
						System.out.println("Failed to send data to JMS topic. Retrying...");
						Thread.sleep(1000);
						if (System.currentTimeMillis()-beginTime > 15000) {
							System.out.println("Can't connect to JMS topic.");
							return;
						}
					} 
				}

			}
		}

	}

	
	/*
	 * Fun��o para ler um ficheiro
	 */
	private static String readFile(File file) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			return sb.toString();
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("Cannot open/read file '" + file.getName() + "'");
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				//e.printStackTrace();
				System.out.println("Cannot close file '" + file.getName() +"'");
			}
		}
		return null;
	}

	
	/*
	 * Fun��o para fazer o parsing da p�gina e preencher os objetos Java.
	 */
	private static void collectNews() {
		System.out.println("Parsing CNN web page");

		String[] region = {"US"/*, "AFRICA","ASIA", "EUROPE","LATINAMERICA", "MIDDLEEAST"*/};
		for (String r : region) {
			System.out.println("\nParsing news from " + r);

			Document doc = null;
			try {
				// Criar uma conex�o � p�gina
				doc = Jsoup.connect("http://edition.cnn.com/" + r).timeout(0).get();
				
				// Fazer o parsing atrav�s de ficheiros
				/*File input = new File("backup/" + r + ".htm");
				doc = Jsoup.parse(input, "UTF-8", "http://edition.cnn.com/");*/
			} catch (IOException e) {
				//e.printStackTrace();
				System.out.println("Cannot connect to website");
			}

			// Criar objecto Regi�o
			Region regiao = new Region();
			regiao.setName(r);

			// Criar novo array de not�cias
			noticias = new ArrayList<News>();

			/* Ir buscar as not�cias "THE LATEST" */
			Elements latest = doc.select("#cnn_mtt1rgtarea a");

			for (Element l : latest) {
				/*
				 * S� interessam not�cias dentro do site da CNN e com uma estrutura comum
				 */
				if (l.attr("abs:href").contains("edition.cnn.com")
						&& !l.attr("abs:href").contains("video")
						&& !l.attr("abs:href").contains("gallery")
						&& !l.attr("abs:href").contains("SPECIALS")
						&& !l.attr("abs:href").contains("SPORT")) {
					trataNoticia(l.attr("abs:href"));
				}
			}

			/* Ir buscar outras not�cias */
			Elements outras = doc.select(".cnn_mtt1imghtitle a");

			for (Element o : outras) {
				/*
				 * S� interessam not�cias dentro do site da CNN e com uma estrutura comum
				 */
				if (o.attr("abs:href").contains("edition.cnn.com")
						&& !o.attr("abs:href").contains("video")
						&& !o.attr("abs:href").contains("gallery")
						&& !o.attr("abs:href").contains("SPECIALS")
						&& !o.attr("abs:href").contains("SPORT")) {
					trataNoticia(o.attr("abs:href"));
				}
			}

			// Associar not�cias � regi�o
			regiao.setNews(noticias);

			// Adicionar regi�o ao array de Regi�es
			regioes.add(regiao);

			// Limpar array de not�cias
			noticias.clear();
		}

		// Associar regi�es ao objecto cnn
		cnn.setRegion(regioes);
	}

	
	/*
	 * Fun��o para tratar de cada not�cia, isto �, recolher as informa��es necess�rias. 
	 * Par�metro de entrada: URL da not�cia.
	 */
	private static void trataNoticia(String url) {

		/* Criar uma conex�o � p�gina */
		Document doc = null;
		try {
			doc = Jsoup.connect(url).timeout(0).get();
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("Cannot connect to " + url);
			return;
		}

		// T�tulo da not�cia
		String title = doc.select("h1").text();
		System.out.println("Title: " + title);

		// Highlights
		ArrayList<String> highlights = new ArrayList<String>();
		Elements hl = doc.select(".cnn_strylctcntr .cnn_bulletbin li");
		for (Element h : hl) {
			highlights.add(h.text());
		}

		// Data
		String date = doc.select(".cnn_strytmstmp").text();
		Calendar cal = trataData(date);

		// Autor
		ArrayList<String> authors = new ArrayList<String>();
		Elements auth = doc.select(".cnnByLine strong a");
		if (auth.isEmpty())
			auth = doc.select(".cnnByLine strong");
		for (Element a : auth) {
			if(a.text().equals(",") || a.text().equals(", ") || a.text().equals(" ") || a.text().equals("")){
				
			}
			else if (a.text().split(", ").length > 1) {
				authors.add(a.text().split(", ")[0]);
				authors.add(a.text().split(", ")[1]);
			} else {
				authors.add(a.text().split(",")[0]);
			}
		}
		for(String a:authors){
			System.out.println(a);
		}

		// Texto
		String text = doc.select(".cnn_strycntntlft p").text();

		// URL da foto
		String photoURL = null;
		Elements metaOgImage = doc.select("meta[property=og:image]");
		if (metaOgImage != null) {
			photoURL = metaOgImage.attr("content");
		}

		// URL do v�deo
		String videoURL = doc.getElementsByClass("OUTBRAIN").attr("data-src");

		
		// Criar objecto News
		News n = new News();
		n.setTitle(title);
		n.setUrl(url);
		n.setHighlights(highlights);
		Date d = new Date();
		d.setDay(cal.get(Calendar.DAY_OF_MONTH));
		d.setMonth(cal.get(Calendar.MONTH) + 1);
		d.setYear(cal.get(Calendar.YEAR));
		int hora = cal.get(Calendar.HOUR_OF_DAY) * 100 + cal.get(Calendar.MINUTE);
		d.setHour(hora);
		n.setDate(d);
		n.setAuthor(authors);
		n.setText(text);
		n.setPhotoURL(photoURL);
		n.setVideoURL(videoURL);

		// Adicionar not�cia ao array de not�cias
		noticias.add(n);
	}

	
	/*
	 * Fun��o para criar a data no formato dd/MM/aaa HHmm
	 */
	private static Calendar trataData(String data) {
		int[] date = breakDate(data);
		
		Calendar calendar = new GregorianCalendar(date[2], date[0], date[1], date[3], date[4]);

		return calendar;

	}

	
	/*
	 * Fun��o para extrair dados necess�rios da data retirada do site
	 */
	private static int[] breakDate(String data) {
		int[] date = new int[5];
		int aux = 0;
		String a;
		StringTokenizer st = new StringTokenizer(data);
		while (st.hasMoreTokens()) {
			a = st.nextToken();
			
			switch (a) {
			case "January": 	date[aux] = 0; 	aux++; 	break;
			case "February": 	date[aux] = 1; 	aux++; 	break;
			case "March": 		date[aux] = 2; 	aux++; 	break;
			case "April": 		date[aux] = 3; 	aux++; 	break;
			case "May": 		date[aux] = 4; 	aux++; 	break;
			case "June": 		date[aux] = 5; 	aux++; 	break;
			case "July": 		date[aux] = 6; 	aux++; 	break;
			case "August": 		date[aux] = 7; 	aux++; 	break;
			case "September": 	date[aux] = 8; 	aux++; 	break;
			case "October": 	date[aux] = 9; 	aux++; 	break;
			case "November": 	date[aux] = 10; aux++; 	break;
			case "December": 	date[aux] = 11; aux++; 	break;
			case "--":
			case "Updated":
				break;
			case "GMT":
				return date;
			default:
				if (aux < 3) {
					date[aux] = Integer.parseInt(a.split(",")[0]);
					aux++;
					break;
				} else {
					date[aux] = Integer.parseInt(a.substring(0, 2));
					aux++;
					date[aux] = Integer.parseInt(a.substring(2, 4));
					aux++;
					break;
				}

			}
		}
		
		return date;
	}

	
	/*
	 * Fun��o para extrair a informa��o para um documento XML
	 */
	private static void javaToXML() throws InterruptedException {

		try {
			// Create JAXB context and initializing Marshaller
			JAXBContext jc = JAXBContext.newInstance(Cnn.class);
			Marshaller m = jc.createMarshaller();

			// For getting nice formatted output
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			// Specify the name of xml file to be created
			// File XMLFile = new File("GeradoPeloMarshaller.xml");

			StringWriter strWriter = new StringWriter();

			// Writing to XML file
			// m.marshal(cnn, XMLFile);
			
			// Writing to StringWriter
			m.marshal(cnn, strWriter);
			
			// Writing to console
			// m.marshal(cnn, System.out);
			
			long beginTime = System.currentTimeMillis();
			
			// Tentar publicar dados
			while (true) {
				try {
					System.out.println("Publishing parsed data");
					Sender s = new Sender();
					s.send(strWriter.toString());
					s.close();
					break;
				} catch (JMSException e) {
					// e.printStackTrace();
					System.out.println("Trying again in 1 second");
					Thread.sleep(1000);
					if (System.currentTimeMillis() - beginTime >= 15000){
						writeLog(strWriter.toString());
						System.out.println("Not published! Saved to a log file");
						return;
					}
				}
			}

		} catch (JAXBException e) {
			//e.printStackTrace();
			System.out.println("Failed to marshal data");
		}
	}

	
	/*
	 * Fun��o para guardar logs em disco
	 */
	private static void writeLog(String string) {
		try {
			PrintWriter out = new PrintWriter("logs/log" + System.currentTimeMillis() + ".txt");
			out.println(string);
			out.close();
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			System.out.println("Cannot create log file");
		}

	}

	
	// Verificar extra��o correta das informa��es
	private static void checkInfo() {
		System.out.println("CNN NEWS\n");

		for (int i = 0; i < cnn.getRegion().size(); i++) {
			System.out.println("REGI�O: " + cnn.getRegion().get(i).getName());
			for (int j = 0; j < cnn.getRegion().get(i).getNews().size(); j++) {
				System.out.println("\nNOT�CIA:");
				System.out.println("Title: " + cnn.getRegion().get(i).getNews().get(j).getTitle());
				System.out.println("URL: " + cnn.getRegion().get(i).getNews().get(j).getUrl());
				System.out.println("Highlights:");
				for (int k = 0; k < cnn.getRegion().get(i).getNews().get(j).getHighlights().size(); k++) {
					System.out.println("\t* " + cnn.getRegion().get(i).getNews().get(j).getHighlights().get(k));
				}
				System.out.println("Date: " + cnn.getRegion().get(i).getNews().get(j).getDate().getDay()
						+ "/" + cnn.getRegion().get(i).getNews().get(j).getDate().getMonth() + 1
						+ "/" + cnn.getRegion().get(i).getNews().get(j).getDate().getYear()
						+ " " + cnn.getRegion().get(i).getNews().get(j).getDate().getHour());
				System.out.println("Author(s): ");
				for (int k = 0; k < cnn.getRegion().get(i).getNews().get(j).getAuthor().size(); k++) {
					System.out.println("\t* " + cnn.getRegion().get(i).getNews().get(j).getAuthor().get(k));
				}
				System.out.println("News text: " + cnn.getRegion().get(i).getNews().get(j).getText());
				System.out.println("Photo URL: " + cnn.getRegion().get(i).getNews().get(j).getPhotoURL());
				System.out.println("Video URL: " + cnn.getRegion().get(i).getNews().get(j).getVideoURL());
			}
		}

	}

}
