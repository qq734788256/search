<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>音乐</title>
</head>
<body>
<div id="page">

  <div id="searchForm">
    <fieldset>
      <input id="key" type="text" />
      <input type="button" id="searchButton" />
      <div id="searchInContainer">
        <label>热门搜索：</label>
        <label style="color:#FFA500">成都</label>
        <label style="color:#98FB98">五月天</label>
      </div>
    </fieldset>
  </div>
  <!--<div id="resultsDiv"></div>-->
</div>
<div id="wrapper">
  <div id="tabs" >
    <ul>
      <li><a href="#xiami" title="">虾米音乐</a></li>
      <li><a href="#qq" title="">QQ音乐</a></li>
      <li><a href="#kugou" title="">酷狗音乐</a></li>
      <li><a href="#baidu" title="">百度音乐</a></li>
    </ul>
    <div id="tabs_container" style="color:black;">
      <div id="xiami">
        <!-- 虾米音乐区 -->
        <table>
        </table>
      </div>
      <div id="qq">
        <!-- QQ音乐区 -->
        <table>
        </table>
      </div>
      <div id="kugou">
        <!-- 酷狗音乐区 -->
        <table>
        </table>
      </div>
      <div id="baidu">
        <!-- 百度音乐区 -->
        <table>
        </table>
      </div>
    </div><!--End tabs container-->
  </div><!--End tabs-->
</div>
<link rel="stylesheet" type="text/css" href="css/styles.css" />
<link href='css/tabulous.css' rel='stylesheet' type='text/css'>
<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script src="js/script.js"></script>
<script type="text/javascript" src="js/tabulous.js"></script>
<script type="text/javascript">
  $(document).ready(function($) {
    $('#tabs').tabulous({
      effect: 'scale'
    });
    $("#searchButton").click(function(){
      var key = $("#key").val();
      if(key == null || key == ""){
        return;
      }
      // 虾米
      $.ajax({
        url:'/search/msc.smc?plat=xiami&key='+key,
        method:'GET',
        dataType:'json',
        success:function(data){
          if(data.c == 0){
            $("#xiami table").html("");
            var content = "";
            var vheight = 45;
            $.each(data.musicGroup[0].musics,function(i,n){
              vheight = vheight + 21;
              content = content + "<tr>"+
                      "<td width='40px' align='center' style='color:black;overflow: hidden; text-overflow:ellipsis;white-space: nowrap;'>试听</td>"+
                      "<td width='130px' style='color: #FF4500;overflow: hidden; text-overflow:ellipsis;white-space: nowrap;'>"+n.fileName+"</td>"+
                      "<td width='50px' align='right' style='color: #FF4500;overflow: hidden; text-overflow:ellipsis;white-space: nowrap;'>歌手：</td>"+
                      "<td width='130px' align='left'' style='color: #FF4500;overflow: hidden; text-overflow:ellipsis;white-space: nowrap;'>"+n.singer+"</td>"+
                      "<td width='180px'' align='left' style='color: #FF4500;overflow: hidden; text-overflow:ellipsis;white-space: nowrap;'>专辑："+n.albumname+"</td>"+
                      "</tr>"
            });
            $("#tabs_container").height(vheight);
            $("#xiami table").html(content);
          }
        },error:function(data){

        }
      });
      // qq
      $.ajax({
        url:'/search/msc.smc?plat=qq&key='+key,
        method:'GET',
        dataType:'json',
        success:function(data){
          if(data.c == 0){
            $("#qq table").html("");
            var content = "";
            $.each(data.musicGroup[0].musics,function(i,n){
              content = content + "<tr>"+
                      "<td width='40px' align='center' style='color:black;overflow: hidden; text-overflow:ellipsis;white-space: nowrap;'>试听</td>"+
                      "<td width='130px' style='color: #FF4500;overflow: hidden; text-overflow:ellipsis;white-space: nowrap;'>"+n.fileName+"</td>"+
                      "<td width='50px' align='right' style='color: #FF4500;overflow: hidden; text-overflow:ellipsis;white-space: nowrap;'>歌手：</td>"+
                      "<td width='130px' align='left'' style='color: #FF4500;overflow: hidden; text-overflow:ellipsis;white-space: nowrap;'>"+n.singer+"</td>"+
                      "<td width='180px'' align='left' style='color: #FF4500;overflow: hidden; text-overflow:ellipsis;white-space: nowrap;'>专辑："+n.albumname+"</td>"+
                      "</tr>"
            });
            $("#qq table").html(content);
          }
        },error:function(data){

        }
      });
      // kugou
      $.ajax({
        url:'/search/msc.smc?plat=kugou&key='+key,
        method:'GET',
        dataType:'json',
        success:function(data){
          if(data.c == 0){
            $("#kugou table").html("");
            var content = "";
            $.each(data.musicGroup[0].musics,function(i,n){
              content = content + "<tr>"+
                      "<td width='40px' align='center' style='color:black;overflow: hidden; text-overflow:ellipsis;white-space: nowrap;'>试听</td>"+
                      "<td width='130px' style='color: #FF4500;overflow: hidden; text-overflow:ellipsis;white-space: nowrap;'>"+n.fileName+"</td>"+
                      "<td width='50px' align='right' style='color: #FF4500;overflow: hidden; text-overflow:ellipsis;white-space: nowrap;'>歌手：</td>"+
                      "<td width='130px' align='left'' style='color: #FF4500;overflow: hidden; text-overflow:ellipsis;white-space: nowrap;'>"+n.singer+"</td>"+
                      "<td width='180px'' align='left' style='color: #FF4500;overflow: hidden; text-overflow:ellipsis;white-space: nowrap;'>专辑："+n.albumname+"</td>"+
                      "</tr>"
            });
            $("#kugou table").html(content);
          }
        },error:function(data){

        }
      });
      // baidu
      $.ajax({
        url:'/search/msc.smc?plat=baidu&key='+key,
        method:'GET',
        dataType:'json',
        success:function(data){
          if(data.c == 0){
            $("#baidu table").html("");
            var content = "";
            $.each(data.musicGroup[0].musics,function(i,n){
              content = content + "<tr>"+
                      "<td width='40px' align='center' style='color:black;overflow: hidden; text-overflow:ellipsis;white-space: nowrap;'>试听</td>"+
                      "<td width='130px' style='color: #FF4500;overflow: hidden; text-overflow:ellipsis;white-space: nowrap;'>"+n.fileName+"</td>"+
                      "<td width='50px' align='right' style='color: #FF4500;overflow: hidden; text-overflow:ellipsis;white-space: nowrap;'>歌手：</td>"+
                      "<td width='130px' align='left'' style='color: #FF4500;overflow: hidden; text-overflow:ellipsis;white-space: nowrap;'>"+n.singer+"</td>"+
                      "<td width='180px'' align='left' style='color: #FF4500;overflow: hidden; text-overflow:ellipsis;white-space: nowrap;'>专辑："+n.albumname+"</td>"+
                      "</tr>"
            });
            $("#baidu table").html(content);
          }
        },error:function(data){

        }
      });
    });
  });
</script>
</body>
</html>