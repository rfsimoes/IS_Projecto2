var cnnsocial={"apiconfig":[],"analytics":"o","setanalytics":function(a){cnnsocial.analytics=a;},"addurlparam":function(a,d,c){var b=/\?.+$/;if(b.test(a)){return a+"&"+d+"="+c;}else{return a+"?"+d+"="+c;}},"sites":{"facebook":{"buildurl":function(a){return"http://www.facebook.com/share.php?u="+encodeURIComponent(cnnsocial.addurlparam(a.url,"sr","sharebar_facebook"))+"&title="+encodeURIComponent(a.title);},"popwidth":550,"popheight":420,"popscroll":"no","loadapi":function(a){(function(f,b){var e,h="facebook-jssdk",c=f.getElementsByTagName("script")[0];if(f.getElementById(h)){return;}e=f.createElement("script");e.id=h;e.async=true;e.src="//connect.facebook.net/en_US/all"+(b?"/debug":"")+".js";c.parentNode.insertBefore(e,c);}(document,false));window.fbAsyncInit=function(){FB.init({appId:"80401312489",status:true,cookie:true,xfbml:true,oauth:true});FB.Event.subscribe("edge.create",function(b){window.cnnsocial.share.track({"type":"click","site":"facebook"});});if(typeof a==="function"){a.apply();}};}},"twitter":{"buildurl":function(a){return"http://twitter.com/intent/tweet?url="+encodeURIComponent(cnnsocial.addurlparam(a.url,"sr","sharebar_twitter"))+"&text="+encodeURIComponent(a.title);},"popwidth":550,"popheight":460,"popscroll":"no","loadapi":function(a){window.twttr=(function(h,c,j){var b,f,e=h.getElementsByTagName(c)[0];if(h.getElementById(j)){return;}f=h.createElement(c);f.id=j;f.src="//platform.twitter.com/widgets.js";e.parentNode.insertBefore(f,e);return window.twttr||(b={_e:[],ready:function(d){b._e.push(d);}});}(document,"script","twitter-wjs"));window.twttr.ready(function(b){b.events.bind("click",function(c){window.cnnsocial.share.track({"type":"click","site":"twitter"});});if(typeof a==="function"){a.apply();}});}},"googleplus":{"buildurl":function(a){return"https://plus.google.com/share?url="+encodeURIComponent(cnnsocial.addurlparam(a.url,"sr","sharebar_google"))+"&hl=en";},"popwidth":600,"popheight":600,"popscroll":"yes","loadapi":function(a){jQuery.getScript("https://apis.google.com/js/plusone.js",function b(){if(typeof a==="function"){a.apply();}});}},"linkedin":{"buildurl":function(a){return"http://www.linkedin.com/shareArticle?mini=true&url="+encodeURIComponent(cnnsocial.addurlparam(a.url,"sr","sharebar_linkedin"))+"&title="+encodeURIComponent(a.title);},"popwidth":550,"popheight":570,"popscroll":"no","loadapi":function(a){jQuery.getScript("http://platform.linkedin.com/in.js",function b(){if(typeof a==="function"){a.apply();}});}},"digg":{"buildurl":function(a){return"http://www.digg.com/submit?phase=2&url="+encodeURIComponent(cnnsocial.addurlparam(a.url,"sr","digg"))+"&title="+encodeURIComponent(a.title);},"popwidth":970,"popheight":500,"popscroll":"yes"},"stumbleupon":{"buildurl":function(a){return"http://www.stumbleupon.com/submit?url="+encodeURIComponent(cnnsocial.addurlparam(a.url,"sr","stumbleupon"))+"&title="+encodeURIComponent(a.title);},"popwidth":750,"popheight":500,"popscroll":"yes"},"delicious":{"buildurl":function(a){return"http://del.icio.us/post?url="+encodeURIComponent(cnnsocial.addurlparam(a.url,"sr","delicious"))+"&title="+encodeURIComponent(a.title);},"popwidth":750,"popheight":500,"popscroll":"yes"},"technorati":{"buildurl":function(a){return"http://technorati.com/faves?add="+encodeURIComponent(cnnsocial.addurlparam(a.url,"sr","technorati"))+"&title="+encodeURIComponent(a.title);},"popwidth":750,"popheight":500,"popscroll":"yes"},"posterous":{"buildurl":function(a){return"http://posterous.com/share?linkto="+encodeURIComponent(cnnsocial.addurlparam(a.url,"sr","posterous"));},"popwidth":1020,"popheight":650,"popscroll":"yes"},"tumblr":{"buildurl":function(a){return"http://www.tumblr.com/share?v=3&u="+encodeURIComponent(cnnsocial.addurlparam(a.url,"sr","tumblr"))+"&t="+encodeURIComponent(a.title);},"popwidth":750,"popheight":500,"popscroll":"yes"},"reddit":{"buildurl":function(a){return"http://www.reddit.com/submit?url="+encodeURIComponent(cnnsocial.addurlparam(a.url,"sr","reddit"))+"&title="+encodeURIComponent(a.title);},"popwidth":750,"popheight":500,"popscroll":"yes"},"googlebookmarks":{"buildurl":function(a){return"http://www.google.com/bookmarks/mark?op=edit&bkmk="+encodeURIComponent(cnnsocial.addurlparam(a.url,"sr","googlebookmarks"))+"&title="+encodeURIComponent(a.title);},"popwidth":750,"popheight":500,"popscroll":"yes"},"newsvine":{"buildurl":function(a){return"http://www.newsvine.com/_tools/seed&save?u="+encodeURIComponent(cnnsocial.addurlparam(a.url,"sr","newsvine"))+"&h="+encodeURIComponent(a.title);},"popwidth":1020,"popheight":500},"ping.fm":{"buildurl":function(a){return"http://ping.fm/ref/?link="+encodeURIComponent(cnnsocial.addurlparam(a.url,"sr","ping.fm"))+"&title="+encodeURIComponent(a.title);},"popwidth":750,"popheight":500,"popscroll":"yes"},"evernote":{"buildurl":function(a){return"http://www.evernote.com/clip.action?url="+encodeURIComponent(cnnsocial.addurlparam(a.url,"sr","evernote"))+"&title="+encodeURIComponent(a.title);},"popwidth":980,"popheight":500,"popscroll":"yes"},"friendfeed":{"buildurl":function(a){return"http://www.friendfeed.com/share?url="+encodeURIComponent(cnnsocial.addurlparam(a.url,"sr","friendfeed"))+"&title="+encodeURIComponent(a.title);},"popwidth":750,"popheight":500,"popscroll":"yes"}},"share":{},"setapiconfig":function(a){cnnsocial.apiconfig=a;},"init":function(){var b=cnnsocial.apiconfig;for(var a=0;a<b.length;a++){var c=b[a];if(typeof c.success==="function"){cnnsocial.sites[c.site].loadapi(c.success);}else{cnnsocial.sites[c.site].loadapi();}}if(typeof cnnsocial.share.data.bars!="undefined"){cnnsocial.share.init();}}};cnnsocial.share={"data":{"bars":{}},"addconfig":function(a){cnnsocial.share.data.bars[a.id]=a;},"setconfig":function(a){for(var b=0;b<a.length;b++){cnnsocial.share.addconfig(a[b]);}},"updateurl":function(a){},"updatesingleurl":function(c,b,a){var d=cnnsocial.share.data.bars[c];d.url=b;d.title=a;cnnsocial.share.data.bars[c]=d;},"click":function(c,b){if(window.console){console.log("clicked "+b+" in: "+c);}var d=cnnsocial.share.data.bars[c];var a=cnnsocial.sites[b];cnnsocial.share.popup(a.buildurl(d),b,a.popwidth,a.popheight,a.popscroll);cnnsocial.share.track({"type":"click","site":b});},"popup":function(c,d,e,b,a){return window.open(c,"cnn_pop_"+d,"width="+e+",height="+b+",resizable=yes,scrollbars="+a);},"track":function(a){if(window.console){console.log(a);}try{if(cnnsocial.analytics==="o"){if(jsmd){if(a.type==="click"){jsmd.trackMetrics("social-click",{"clickObj":{"socialType":a.site+"_click"}});}else{if(a.type==="success"){jsmd.trackMetrics("social-click",{"clickObj":{"socialType":a.site+"_post"}});}}}}else{if(cnnsocial.analytics==="b"){if(typeof(bangoSocial)==="function"){bangoSocial({"socialMedia":a.site});}}}}catch(b){if(window.console){console.log("error thrown while registering click tracking. Message - "+b.message);}}},"init":function(){var c=cnnsocial.share.data.bars;for(var d in c){var b=c[d];var a="#"+b.id+" .c_sharebar_cntr";jQuery(a).removeClass("c_sharebar_loading");}}};var cnnsocial_google_click=function(a){if(a.state==="on"){window.cnnsocial.share.track({"type":"click","site":"googleplus"});}else{if(a.state==="off"){}}};var cnnsocial_linkedin_click=function(){window.cnnsocial.share.track({"type":"click","site":"linkedin"});};var cnnWeather=(typeof Class=="object")?Class.create():{};cnnWeather={config:{activated:true,weatherCdnPath:"http://i.cdn.turner.com/cnn/.e/img/3.0/weather/",weatherUrl:"http://svcs.cnn.com/weather/getForecast",iconSmallPath:"01/",iconLargePath:"03/",shortName:["SUN","MON","TUE","WED","THU","FRI","SAT"],weatherDivs:["cnnLWPWeather","user_weather"],weatherLink:"http://weather.cnn.com/weather/",weatherLinkIntl:"http://weather.edition.cnn.com/weather/intl/",forecastLink:"",requestTimer:"",requestAttempts:30,editionPref:""},data:{celsius:false,locCode:"",zipCode:""},init:function(){if(this.config.activated){this.config.requestTimer=setInterval(this.testCSIReady,500);}},testCSIReady:function(){cnnWeather.config.requestAttempts--;
if(typeof CSIManager.getInstance!=="undefined"){cnnWeather.setData();clearInterval(cnnWeather.config.requestTimer);}else{if(typeof CSIManager.getInstance==="undefined"&&cnnWeather.config.requestAttempts>0){cnnWeather.testCSIReady();}else{}}},setData:function(){if(typeof CNN_getCookies==="function"){var h=CNN_getCookies();var d=h["lwp.weather"]||null;if(location.hostname.indexOf("edition")>-1&&h["default.temp.units"]==="true"){this.data.celsius=true;}else{this.data.celsius=false;}if(d){var b=unescape(d).split("|");var f=b[0];if(d.indexOf("~")==-1){f=d.replace("|","~");}var a=b[0].split("~");this.data.locCode=a[0];this.data.zipCode=a[1];this.requestWeather();}else{var e=this.randomCityZip();this.data.zipCode=e[0];this.data.locCode=e[1];this.requestWeather();}if(location.hostname.indexOf("edition")>-1){this.config.forecastLink=this.config.weatherLinkIntl+"forecast.jsp?&zipCode="+this.data.zipCode;}else{this.config.forecastLink=this.config.weatherLink+"forecast.jsp?zipCode="+this.data.zipCode;}}else{}},requestWeather:function(){var a=this.config.weatherUrl;var c="mode=json_html&zipCode="+this.data.zipCode+"&locCode="+this.data.locCode+"&celcius="+this.data.celsius;var b={url:a,args:c,domId:false,funcObj:this.requestHandler,breakCache:false};CSIManager.getInstance().callObject(b,"requestWeather");},requestHandler:function(e){if(typeof e==="object"){cnnWeather.data=e[0];for(var c=0;c<cnnWeather.data.forecast.days.length;c++){var a=parseFloat(cnnWeather.data.forecast.days[c].dayDate.day);cnnWeather.data.forecast.days[c].shortName=cnnWeather.config.shortName[a];var b=parseFloat(cnnWeather.data.forecast.days[c].high);var d=parseFloat(cnnWeather.data.forecast.days[c].low);cnnWeather.data.forecast.days[c].average=Math.floor((b+d)/2);cnnWeather.data.forecast.days[c].icon=cnnWeather.gif2png(cnnWeather.data.forecast.days[c].icon);}cnnWeather.ui();}else{}},ui:function(){for(i=0;i<cnnWeather.config.weatherDivs.length;i++){var a=cnnWeather.config.weatherDivs[i];var b=$(a);if(typeof cnnWeather.html[a]==="function"&&b){cnnWeather.html[a].apply();}else{}}},randomCityZip:function(){if(location.hostname.indexOf("edition")>-1){var d=["336736767676"];var c=["EGLL"];var a=[d[0],c[0]];return a;}else{var d=["27374","95614","87901","74446","31041","65570","29112","79031","25902"];var c=["NC26","CAPL","NM12","MUSX","09GA","TBN","USSC08","30TX","BLF"];var b=Math.floor(Math.random()*d.length);var a=[d[b],c[b]];return a;}},gif2png:function(a){var a=a.split(".");a=a[0];a+=".png";return a;}};cnnWeather.html={};cnnWeather.html.cnnLWPWeather=function(){if(cnnWeather.data.currentConditions.valid==false){cnnWeather.data.currentConditions.temperature="--";cnnWeather.data.currentConditions.icon="na_sm";}var a=(cnnWeather.data.currentConditions.temperature_S==="N/A")?'<span style="font-size: 50%;">N/A</span>':cnnWeather.data.currentConditions.temperature_S+"&deg;";var c='<div class="cnn_ftrwthr1">';c+='	<a href="'+cnnWeather.config.forecastLink+'" title=""><img src="'+cnnWeather.config.weatherCdnPath+cnnWeather.config.iconLargePath+cnnWeather.data.currentConditions.icon+'.png" width="54" height="47" alt="" class="cnn_ie6png" border="0"></a>';c+="</div>";c+='<div class="cnn_ftrwthr2"><div class="cnn_ftrwthr3">'+a+"</div>";c+='<div class="cnn_ftrwthr4"><div>HI '+cnnWeather.data.forecast.days[0].high+'&deg;<span style="padding-left:7px">LO '+cnnWeather.data.forecast.days[0].low+"&deg;</span></div>";c+='<div style="clear:left; line-height: 13px"><span style="padding-right:6px"><strong>'+cnnWeather.data.location.city+", "+cnnWeather.data.location.stateOrCountry+"</strong>";c+='</span><a href="'+cnnWeather.config.forecastLink+'">Weather forecast</a></div></div></div>';var b=$("cnnLWPWeather");$("cnnLWPWeather").innerHTML=c;$("cnnLWPWeather").style.visibility="visible";};cnnWeather.html.user_weather=function(){$("weather_act").innerHTML=cnnWeather.data.location.city+", "+cnnWeather.data.location.stateOrCountry;$("weather_format_img").href=cnnWeather.config.forecastLink;var a=(cnnWeather.data.currentConditions.temperature_S==="N/A")?'<span style="font-size: 50%;">N/A</span>':cnnWeather.data.currentConditions.temperature_S+"&deg;";var b="";b+='<a href="'+cnnWeather.config.forecastLink+'" title=""><img class="cnn_ie6png" src="'+cnnWeather.config.weatherCdnPath+cnnWeather.config.iconLargePath+cnnWeather.data.currentConditions.icon+'.png" width="57" height="47" alt="Current Weather" ></a>';$("weather_icon").innerHTML=b;b="";b+="<h1>"+a+"</h1>";b+='<h2 id="weather_hi">HI '+cnnWeather.data.forecast.days[0].high+"&deg;</h2>";b+='<h2 id="weather_lo">LO '+cnnWeather.data.forecast.days[0].low+"&deg;</h2>";$("weather_temp").innerHTML=b;b="";b+='<div class="wth_3daycol"><img class="cnn_ie6png" src="'+cnnWeather.config.weatherCdnPath+cnnWeather.config.iconSmallPath+cnnWeather.data.forecast.days[1].icon+'" width="21" height="17" alt="">';b+='<div class="w_day">'+cnnWeather.data.forecast.days[1].shortName+'</div><div class="w_temp">'+cnnWeather.data.forecast.days[1].average+"&deg;</div></div>";b+='<div class="wth_3daycol"><img class="cnn_ie6png" src="'+cnnWeather.config.weatherCdnPath+cnnWeather.config.iconSmallPath+cnnWeather.data.forecast.days[2].icon+'" width="21" height="17" alt="">';b+='<div class="w_day">'+cnnWeather.data.forecast.days[2].shortName+'</div><div class="w_temp">'+cnnWeather.data.forecast.days[2].average+"&deg;</div></div>";b+='<div class="wth_3daycol_last"><img class="cnn_ie6png" src="'+cnnWeather.config.weatherCdnPath+cnnWeather.config.iconSmallPath+cnnWeather.data.forecast.days[3].icon+'" width="21" height="17" alt="" >';b+='<div class="w_day">'+cnnWeather.data.forecast.days[3].shortName+'</div><div class="w_temp">'+cnnWeather.data.forecast.days[3].average+'&deg;</div></div><div class="clear"></div>';$("weather_3day").innerHTML=b;$("user_weather").style.visibility="visible";};Event.observe(window,"load",function(){cnnWeather.init();});var cnn_edtnswtchcnfg={};var t_selectededition="";function cnn_initeditionhtml(a){cnn_edtnisforced=a;CSIManager.getInstance().call("/.element/ssi/misc/3.0/editionvars.html","","cnn_hdr-promptcntnt",cnn_loadeditionhtml);}function cnn_loadeditionhtml(b){cnn_edtnswtchcnfg=b;if(cnn_edtnisforced){if(cnn_edtnisforced==3){if(t_selectededition==""){t_selectededition=allCookies["SelectedEdition"];}var a='<span id="hdr-prompt-text"><form>Please select your default edition:<b><input type="radio" name="cnn_edselect" value="" onclick="cnn_stedtnckie(\'edition\');"';if(t_selectededition=="edition"){a+=" checked";}a+='> International</b><b><input type="radio" name="cnn_edselect" value="" onclick="cnn_stedtnckie(\'www\');"';if(t_selectededition=="www"){a+=" checked";}a+='> U.S.</b><b><input type="radio" name="cnn_edselect" value="" onclick="cnn_stedtnckie(\'mexico\');"';if(t_selectededition=="mexico"){a+=" checked";}a+='> Mexico</b></form></span><img id="cnn_hdr-arrow" src="http://i.cdn.turner.com/cnn/.e/img/3.0/1px.gif" width="1" height="1" class="hdr-arrow-us2 cnn_dynone" />';}else{var a=(cnn_edtnisforced==1)?cnn_edtnswtchcnfg[cnn_edtnswtchver].edtn_msgs.def_msg:cnn_edtnswtchcnfg[cnn_edtnswtchver].edtn_msgs.sm_msg;}t_html=a+cnn_edtnswtchcnfg["edtn_clshtml"];}else{if(cnn_adcntryckie&&cnn_hasCG){t_html=cnn_edtnswtchcnfg[cnn_edtnswtchver].get_cntrymsg(cnn_adcntryckie)+cnn_edtnswtchcnfg["edtn_clshtml"];}else{if(cnn_userbrwsrlng){t_html=cnn_edtnswtchcnfg[cnn_edtnswtchver].edtn_msgs.brwsr_msg+cnn_edtnswtchcnfg["edtn_clshtml"];}}}cnn_shweditionhtml();return t_html;}function cnn_shweditionhtml(){if(Prototype.Browser.IE&&parseInt(navigator.userAgent.substring(navigator.userAgent.indexOf("MSIE")+5))==6){$("cnn_hdr-prompt").style.display="block";}else{Effect.SlideDown("cnn_hdr-prompt",{duration:0.7,afterFinish:function(){$("cnn_hdr-arrow").style.display="block";}});}}function cnn_clseditionhtml(){if(Prototype.Browser.IE&&parseInt(navigator.userAgent.substring(navigator.userAgent.indexOf("MSIE")+5))==6){$("cnn_hdr-prompt").style.display="none";}else{Effect.SlideUp("cnn_hdr-prompt",{duration:0.5});
}}function cnn_delayeditionhtml(a,b){if(!b){b=720;}if((b==168)&&!cnn_hasCG){cnn_stedtnckie("www");}else{CNN_setCookie("delayeditionhtml","delayed",b,"/",".cnn.com");}if(!a){cnn_clseditionhtml();}}function omniSwitchEdition(b){var a=s_gi("cnn3global");a.trackingServer="metrics.cnn.com";if(b=="edition"){b="intl";}if(b=="www"){b="us";}b=b+"pref";a.tl(this,"o",b);}function cnn_stedtnckie(b,a){CNN_setCookie("SelectedEdition",b,854400,"/",".cnn.com");omniSwitchEdition(b);t_selectededition=b;if(a){if(b=="www"){location.href="http://www.cnn.com/";}else{location.href="http://edition.cnn.com/";}}else{cnn_clseditionhtml();}}var cnn_queryargs=cnn_geturlqargs();var cnn_adcntryckie;var cnn_edtnisforced=0;var cnn_hasCG=false;var cnn_hasforeignb=false;var cnn_edtnChoice=allCookies["SelectedEdition"];var cnn_userbrwsrlng=(navigator.language)?navigator.language:navigator.userLanguage;if(allCookies["CG"]){cnn_adcntryckie=allCookies["CG"];cnn_adcntryckie=cnn_adcntryckie.split(":")[0];if((cnn_adcntryckie!="A2")&&(cnn_adcntryckie!="US")&&(cnn_adcntryckie!="A1")&&(cnn_adcntryckie!="O1")){cnn_hasCG=true;}}if(cnn_queryargs.cnn_shwEDDH){Event.observe(window,"load",function(){cnn_initeditionhtml(1);});}else{if((allCookies["delayeditionhtml"]!="delayed")&&!cnn_edtnChoice){if(cnn_edtnswtchver=="www"){if(cnn_hasCG){Event.observe(window,"load",function(){cnn_initeditionhtml();});}if(!cnn_hasCG&&cnn_userbrwsrlng&&(cnn_userbrwsrlng.toLowerCase()!="en-us")){cnn_hasforeignb=true;Event.observe(window,"load",function(){cnn_initeditionhtml();});}if(!cnn_hasforeignb&&!cnn_hasCG){Event.observe(window,"load",function(){cnn_initeditionhtml(1);});}}else{if(cnn_edtnswtchver=="edition"){if(cnn_adcntryckie=="MX"){Event.observe(window,"load",function(){cnn_initeditionhtml(2);});}else{Event.observe(window,"load",function(){cnn_initeditionhtml(1);});}}}CNN_setCookie("SelectedEdition",cnn_edtnswtchver,48,"/",".cnn.com");}}(function(a,c){if(c){a(document).ready(function(){a.getScript("//consent.truste.com/notice?domain=cnn.com&c=teconsent&text",function(b,h,f){adChoicesIcon='<img id="ad-ch-icn" width="19px" height="15px" style="background:transparent !important;margin:0;padding:0;border:none;position:relative;right:0px;top:0;float:right;" src="http://choices.truste.com/get?name=admarker-icon-tr.png">';adChoices="<a onclick=\"(function(){var s=document.createElement('script');s.src='//preferences.truste.com/webservices/js?domain=turner.com&type=turner&js=2';s.onreadystatechange=function(){if(this.readyState=='loaded'||this.readyState=='complete'){view(trusteId);}};s.onload=function(){view(trusteId);};document.getElementsByTagName('head')[0].appendChild(s);})()\" style=\"cursor:pointer;\" target=\"_blank\">"+adChoicesIcon+"</a>";a.fn.trusteConsent&&(a("div#consent_blackbar").append('<div id="consent-track">'+adChoices+'<div id="consent-wrap"><div id="content-text"><h2>We use cookies to improve your experience on this website. By continuing to browse our site you agree to our use of cookies.</h2></div><div id="content-buttons"><button id="consent-button" type=button><b>I agree</b> - don&apos;t show this message again</button><button id="show-consent" type=button>I want more information / Set my preferences</button></div></div></div>'),a("#consent-track").trusteConsent({consentButton:"#consent-button",showConsent:"#show-consent",cookieButton:"#cookie-button"}));});});a.fn.trusteConsent=function(h){var k=this,j=a.extend({domain:"cnn.com",consentButton:null,showConsent:null,watchEvery:500,watchFor:30000},h),b=function(){k.fadeIn();var d=k.find(j.consentButton);d&&d.click(function(){truste.eu&&truste.eu.actmessage&&truste.eu.actmessage({source:"preference_manager",message:"submit_preferences",data:{value:2}});k.fadeOut();g();});(d=k.find(j.showConsent))&&d.click(function(){truste.eu&&truste.eu.bindMap&&window.open("/services/international.cookie.policy/");});(d=k.find(j.cookieButton))&&d.click(function(){truste.eu&&window.open("/services/international.cookie.policy/");});};g=function(){a("div#footerCookie").append("<span>"+truste.eu.bindMap.icon+"</span>");a("div#footerCookie").css("cursor","pointer");a("div#footerCookie").click(function(){truste.eu&&truste.eu.bindMap&&window.open("/services/international.cookie.policy/");});a("div#footerCookie").show();};h=function(){var d=window.setInterval(function(){0<k.find("div").length&&(b(),window.clearInterval(d));},j.watchEvery);window.setTimeout(function(){window.clearInterval(d);},j.watchFor);};"object"==typeof truste&&("eu"==truste.eu.bindMap.behaviorManager?function(){if(truste.cma&&truste.cma.callApi){var d=j.domain;return("implied"==truste.cma.callApi("getConsent",d,d,d).source)?true:(g(),false);}return !1;}()&&h():a("div#teconsent").hide());return this;};}else{a('#cnn_ftrcntnt a:contains("AdChoices")').removeAttr("onclick").attr("href","/services/ad.choices/");}})(jQuery,!window.cnn_edtnCookieConsentBarDisabled);