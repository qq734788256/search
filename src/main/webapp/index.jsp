<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>音乐</title>
</head>
<body>
<div id="notice">
  如需使用该网站功能，请联系XXXXXXXXXX
</div>
<div id="page">
  <div id="searchForm">
    <fieldset>
      <input id="key" type="text" />
      <input type="button" id="searchButton" />
      <div id="searchInContainer">
        <label>热门搜索：</label>
        <label style="color:#FFA500" onclick="searchMsc('成都');">成都</label>
        <label style="color:#98FB98" onclick="searchMsc('五月天');">五月天</label>
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
<div id="mplayer" style="display: none">
  <audio src="http://fs.open.kugou.com/fc643b5b898eb860793f587b6f8b1c1b/58bfa26c/G092/M0A/07/09/nA0DAFiqtgOAJceEABCAZ7ENfDk317.m4a" controls="controls" autoplay="autoplay" loop="loop">
    Your browser does not support the audio element.
  </audio>
</div>

<link rel="stylesheet" type="text/css" href="css/styles.css" />
<link href='css/tabulous.css' rel='stylesheet' type='text/css'>
<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script src="js/script.js"></script>
<script type="text/javascript" src="js/tabulous.js"></script>
<script type="text/javascript">
  function playMSC(type,hashs){
    if("xiami" == type){
      $("#mplayer audio").attr("src",hashs);
    } else {
      $.ajax({
        url:'/msc/'+type+'/music.smc?hash='+hashs,
        method:'GET',
        dataType:'json',
        success:function(data){
          if(data.c == 0){
            $("#mplayer audio").attr("src",data.music);
          } else if(data.c == 100){
            alert(data.m);
          }
        },error:function(data){

        }
      });
    }
  }
  function searchMsc(key){
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
                    "<td width='40px' align='center' style='color:black;overflow: hidden; text-overflow:ellipsis;white-space: nowrap;'><label style='color: #CD661D' onclick='playMSC(\"xiami\",\""+n.hash+"\")'>试听</label></td>"+
                    "<td width='130px' style='color: #FF4500;overflow: hidden; text-overflow:ellipsis;white-space: nowrap;'>"+n.fileName+"</td>"+
                    "<td width='50px' align='right' style='color: #FF4500;overflow: hidden; text-overflow:ellipsis;white-space: nowrap;'>歌手：</td>"+
                    "<td width='130px' align='left'' style='color: #FF4500;overflow: hidden; text-overflow:ellipsis;white-space: nowrap;'>"+n.singer+"</td>"+
                    "<td width='180px'' align='left' style='color: #FF4500;overflow: hidden; text-overflow:ellipsis;white-space: nowrap;'>专辑："+n.albumname+"</td>"+
                    "</tr>"
          });
          $("#tabs_container").height(vheight);
          $("#xiami table").html(content);
        } else if(data.c == 100){
          alert(data.m);
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
                    "<td width='40px' align='center' style='color:black;overflow: hidden; text-overflow:ellipsis;white-space: nowrap;'><label style='color: #CD661D' onclick='playMSC(\"qq\",\""+n.hash+"\")'>试听</label></td>"+
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
                    "<td width='40px' align='center' style='color:black;overflow: hidden; text-overflow:ellipsis;white-space: nowrap;'><label style='color: #CD661D' onclick='playMSC(\"kugou\",\""+n.hash+"\")'>试听</label></td>"+
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
                    "<td width='40px' align='center' style='color:black;overflow: hidden; text-overflow:ellipsis;white-space: nowrap;'><label style='color: #CD661D' onclick='playMSC(\"baidu\",\""+n.hash+"\")'>试听</label></td>"+
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
  }
  $(document).ready(function($) {
    $('#tabs').tabulous({
      effect: 'scale'
    });
    $("#searchButton").click(function(){
      var key = $("#key").val();
      if(key == null || key == ""){
        return;
      }
      searchMsc(key);
    });
  });

</script>
</body>
</html>