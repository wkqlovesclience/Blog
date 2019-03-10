<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- saved from url=(0021)http://www.gaozhy.cn/ -->
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="renderer" content="webkit">
	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta name="description" content="gaozhy&#39;s homepage">
	<meta name="keywords" content="gaozhy">
	<title>我想静静</title>
	<link rel="shortcut icon" type="image/x-icon" href="http://www.gaozhy.cn/resources/timg.jpg">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/index.css">
	<link rel="stylesheet" media="screen" href="${pageContext.request.contextPath}/static/css/style.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/blog.css">
	<link href="http://blog.java1234.com/favicon.ico" rel="SHORTCUT ICON">
	<script src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery-1.11.2.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/bootstrap3/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/index.js"></script>
	<style type="text/css">
		body {
			padding-top: 10px;
			padding-bottom: 40px;
		}
	</style>
</head>
<body class="overlay">
	<div id="imgs">
	  <!-- <div id="bg1" class="bg"></div> -->
	  <div id="bg2" class="bg"></div>
	  <!-- <div id="bg3" class="bg"></div> -->
	  <div id="bg4" class="bg fadein"></div>
	  <div id="bg5" class="bg"></div>
	  <!-- <div id="bg6" class="bg"></div> -->
	  <!-- <div id="bg7" class="bg"></div> -->
	  <!-- <div id="bg8" class="bg"></div> -->
	  <!-- <div id="bg9" class="bg"></div> -->
	  <div id="bg10" class="bg"></div>
	  <!-- <div id="bg11" class="bg"></div> -->
	  <!-- <div id="bg12" class="bg"></div> -->
	  <div id="bg13" class="bg fadein"></div>
	  <!-- <div id="bg14" class="bg"></div> -->
	  <!-- <div id="bg15" class="bg"></div> -->
	  <!-- <div id="bg16" class="bg"></div> -->
	  <div id="bg17" class="bg"></div>
	  <!-- <div id="bg18" class="bg fadein"></div> -->
	</div>

	<%--<div class="overlay"></div>--%>
	<div id="particles-js"><canvas class="particles-js-canvas-el" width="1920" height="942" style="width: 100%; height: 100%;"></canvas></div>
    <jsp:include page="/mainTemp.jsp"/>



	<div class="footer">
		<div class="time" id="aa">Time:1H 2M 24S </div>
		<div class="copyright">
			© <script>document.write(new Date().getFullYear());</script>/<script>document.write(new Date().getMonth()+1);</script>/<script>document.write(new Date().getDate());</script>-sclience
		</div>
	</div>


<script type="text/javascript">
	// 替换class达到淡入淡出的效果
	function fadeIn(e) {
	  e.className = "bg fadein"
	};

	function fadeOut(e) {
	  e.className = "bg"
	};
	//申明图片数组中当前的轮播图片
	cur_img = document.getElementById("imgs").children.length - 1;
	//图片轮播函数
	function turnImgs(imgs) {
		var imgs = document.getElementById("imgs").children;
		if (cur_img == 0) {
		  fadeOut(imgs[cur_img]);
		  cur_img = imgs.length - 1;
		  fadeIn(imgs[cur_img]);
		} else {
		  fadeOut(imgs[cur_img]);
		  fadeIn(imgs[cur_img - 1]);
		  cur_img--;
		}
	}
	//设置轮播间隔
	setInterval(turnImgs, 8000);


	/* 设置时间 */
	function show_date_time () { 
		window.setTimeout("show_date_time()", 1000); 
		timeold = ((new Date()).getTime()-(new Date("08/26/2016 00:00:00")).getTime());

		sectimeold = timeold/1000;
		secondsold = Math.floor(sectimeold); 
		msPerDay = 24*60*60*1000;

		e_daysold = timeold/msPerDay;
		daysold=Math.floor(e_daysold); 

		e_hrsold=(e_daysold-daysold)*24; 
		hrsold=Math.floor(e_hrsold); 

		e_minsold=(e_hrsold-hrsold)*60; 
		minsold=Math.floor((e_hrsold-hrsold)*60); 
		
		seconds=Math.floor((e_minsold-minsold)*60); 
		aa.innerHTML="Time:"+hrsold+"H "+minsold+"M "+seconds+"S "; 
	}
	show_date_time();
</script>

<script src="${pageContext.request.contextPath}/static/js/particles.js"></script>
<script src="${pageContext.request.contextPath}/static/js/app.js"></script>

</body></html>