var msgid;
var liIndex=1;
var socket=null;

var deviceId=[];
    setInterval(function(){
    socket.send("心跳");
},120000);
var deviceType=[];
var layui=layui.use('layim', function(layim){
    //基础配置

    layim.on('ready', function(options){
        var friend=options.friend;
        var device=[];
        for(var i=0;i<friend.length;i++){
            var friends=friend[i].list;
            for(var j=0;j<friends.length;j++){
                if(friends[j].avatar){
                    deviceType[friends[j].id]={avatar:friends[j].avatar,username:friends[j].username};
                }
            }
        }
         $("#setPanlMin").click();
    });
    layim.config({
        //初始化接口
        init: {
            url: "/layUi/loadLayIm"
            ,data: {}
        }
         ,uploadFile: {
          url: '/layUi/addFileAndBackFileJson'
         ,type: 'post' //默认post
         },
        uploadImage:{
          url:'/layUi/addImgAndBackImgJson'
        },title: '应急指挥' //主面板最小化后显示的名称
        //,skin: [] //新增皮肤
        ,isgroup: true //是否开启群组
        ,chatLog: '/web/BDCommunication/shortHistoryMsg'
        ,find: ''
        ,copyright: true //是否授权
        ,members: {
            url: '/layUi/getMembers'
            ,data: {}
        }
    });
    if ('WebSocket' in window) {
        Medo.getAJax("/web/webSocket/getWebUrl", {}, function (data) {
            var url = data.webUrl;
            socket = new WebSocket(url);
            //接收到消息的回调方法
            socket.onmessage = function (data) {
                var chatlist=$(".layim-chat-list").find("li");
                if(chatlist.length>19){
                   return;
                }
                var content ;
                var data=JSON.parse(data.data);
                if(data.lng!=null&&data.lng>0&&data.lat!=null&&data.lat>0){
                    messageHandle(data);//消息分发到首页地图
                }
                if(data.type==12){
                    content=data.msg+'(来自网络消息)'
                }else if(data.type==13){
                    var id=data.reportId;
                    content='<a href="javascript:void(0);" onclick="jumpHandheldInspections(\''+id+'\')">'+data.msg+'(来自平板巡检报告)</a>'
                }else if(data.type==14){
                    var id=data.reportId;
                    content='<a href="javascript:void(0);" onclick="jumpFlatInspection(\''+id+'\')">'+data.msg+'(来自检手持机巡检报告)</a>'
                }
                else if(data.messageType==104){
                    content=data.msg+'(来自手机短信发送)'
                }else if(data.messageType==105){
                    content=data.msg+'(来自手持机发送北斗短报文)'
                }else{
                    content=data.msg;
                }
                if(data.msg==""||data.msg==undefined){
                    return false;
                }
                if(data.msgSource){
                    content+=data.msgSource;
                }
                var termId=data.termId;
                if(!termId){
                    return false;
                }
                /**
                 * 设置接受消息未读条数
                 */
                if(msgid!=termId) {
                    var msgCount = $("#msgCount" + termId).html();
                    $("#msgCount" + termId).html(++msgCount);
                    $("#msgCount" + termId).show();
                }
                if(!deviceType[termId]){
                    return false;
                }
                layim.getMessage({
                    username: deviceType[termId].username
                    , avatar: deviceType[termId].avatar
                    , id: termId
                    , type: 'friend'
                    , content: content
                    , timestamp:  (new Date(data.time)).format("yyyy-MM-dd hh:mm:ss")
                });
                //监听消息，设置声音
                $("#chatAudio")[0].play();
            };
            //接收到消息的回调方法
            socket.onopen = function () {
                console.log("连接成功");
            }
        });
    }
    var msgType;
    layim.on('chatChange', function(data){
        msgid=data.data.id;
        /*技術活*/
        var chatlist=$(".layim-chat-list").find("li");
        deviceId=[];
        for(var i=0;i<chatlist.length;i++){
            deviceId.push($(chatlist.get(i)).attr("name"));
        }
        if(chatlist.length>19){
            $("li[name=chatLimit]").attr("layim-event","");
        }else{
            $("li[name=chatLimit]").attr("layim-event","chat");
        }
        msgType=data.data.deviceType;

       /* if(msgType==2||msgType==3){
            //北斗短文
            $("#magesstypes"+msgid).val(2);

        }else if(msgType==1){
            //手机短信
            $("#magesstypes"+msgid).val(3);

        }else{
            //网络消息（暂无）
            $("#magesstypes"+msgid).val(1);
        }*/
        showSurplusTimes(msgid)
        /*$("#magesstypes"+msgid).attr("disabled","disabled");*/
        /**
         * 查看未读记录，重新初始化
         */
        if( $("#msgCount"+msgid).html()!=0){
            $("#msgCount"+msgid).html(0);
            $("#msgCount"+msgid).hide();
        }
    });
    layim.setChatMin();

});

 function messageShow(){
     if(deviceId.length>19){
         layer.msg("通讯框达到最大限度,请选择性删除聊天框")
     }
 }

/**
 * 统计输入字符个数
 */
function beytLens(id){

    var value = $("#magesstypes"+id).val();
    var message = $("#textareas"+id).val();
    var length = strLen(message);
    $("#title_time1s"+id).html(79 - length);
    if(length > 79 && (value==2 || value==3)){
        $("#textareas"+id).css("color","#FF0000")
    }else{
        $("#textareas"+id).css("color","#000000")
    }
}
function strLen(str){
    var len = 0, regExp = /^[\u4e00-\u9fa5，。！；‘“”’：、]{0,79}$/;
    for (var i=0; i<str.length; i++) {
        var n= str.substring(i, i+1);
        //alert(n+"是"+((regExp.test(n)) ? "中文":"英文"));
        //判断是不是中文
        if (regExp.test(n)) {
            len += 2;
        } else {
            len ++;
        }
    }
    return len;
}


/**
 * 发送北斗消息时提示发送频率时间
 */
function showSurplusTimes(id){
    var value = $("#magesstypes"+id).val();
    if(value==2){
        $("#title_texts"+id).show();
        $("#title_times"+id).show();
    } else {
        $("#title_texts"+id).hide();
        $("#title_times"+id).hide();
    }
    if(value==2 || value==3){
        $("#surplus_chars"+id).show();
        $("#title_time1s"+id).show();
    } else {
        $("#surplus_chars"+id).hide();
        $("#title_time1s"+id).hide();
    }
}
/**
 * 发送北斗消息频率计时器
 */
function countDownTime(id){
    var t = 60;
    var timer = setInterval(function(){
        if(t > 0){
            t --;
            $("#title_times"+id).html(t);
        }else{
            clearInterval(timer);
        }
    },1000);
}

function showSendType(){
    $(".layim-menu-box").toggle();
}

function hideSendType(index){
    var li=$("#box").find('li');
    if(index==0){
        li[index].className="layim-this";
        li[index+1].className="";
        liIndex=index;
    }else{
        li[index-1].className="layim-this";
        /*li[index-1].className="";*/
        /*liIndex=index;*/
    }
    //$(".layim-menu-box").hide(10000);
    setTimeout('$(".layim-menu-box").hide("slow")',200);
};

/**
 * 消息发送
 * @returns {boolean}
 */
function send_msg(id,mgcontent,type){
    var length = strLen(mgcontent);
    if(type==1 || type==2 || type==3){
        if(mgcontent==""&&!mgcontent){
            layer.msg('请填写发送内容');
            return false;
        }else if(length <= 79 && (type==2 || type==3)){
            var time = $("#title_times"+id).text();
            if(type==2 && time > 0){
                layer.msg('发送频率时间未到');
                return false;
            }else{
                send_messages(mgcontent,type,id);
                return false;
            }
        } else if(type==1){
            send_messages(mgcontent,type,id);
        } else {
            layer.msg('发送字符数超出限制');
            return false;
        }
    }
}

//发送表情
var face;
function sendFace (mgcontent) {
    var i = ["[微笑]", "[嘻嘻]", "[哈哈]", "[可爱]", "[可怜]", "[挖鼻]", "[吃惊]", "[害羞]", "[挤眼]", "[闭嘴]", "[鄙视]", "[爱你]", "[泪]", "[偷笑]", "[亲亲]", "[生病]", "[太开心]", "[白眼]", "[右哼哼]", "[左哼哼]", "[嘘]", "[衰]", "[委屈]", "[吐]", "[哈欠]", "[抱抱]", "[怒]", "[疑问]", "[馋嘴]", "[拜拜]", "[思考]", "[汗]", "[困]", "[睡]", "[钱]", "[失望]", "[酷]", "[色]", "[哼]", "[鼓掌]", "[晕]", "[悲伤]", "[抓狂]", "[黑线]", "[阴险]", "[怒骂]", "[互粉]", "[心]", "[伤心]", "[猪头]", "[熊猫]", "[兔子]", "[ok]", "[耶]", "[good]", "[NO]", "[赞]", "[来]", "[弱]", "[草泥马]", "[神马]", "[囧]", "[浮云]", "[给力]", "[围观]", "[威武]", "[奥特曼]", "[礼物]", "[钟]", "[话筒]", "[蜡烛]", "[蛋糕]"], a = {};
    return layui.each(i, function (i, e) {
        if(mgcontent.indexOf(e)!=-1){
            a[e] = layui.cache.dir + "images/face/" + i + ".gif"
            face=a[e]
        }
    }),a
}
/*var imgUrl;
function sendIMG(mgcontent){
    imgUrl=mgcontent.substring(mgcontent.indexOf("[/")+1,mgcontent.indexOf("]"));
}*/
/*var fileUrl;
var fileName;
function sendFile(mgcontent){
    fileUrl=mgcontent.substring(mgcontent.indexOf("(/")+1,mgcontent.indexOf(")"));
    fileName=mgcontent.substring(mgcontent.lastIndexOf("[")+1,mgcontent.indexOf(":"));
}*/

function send_messages(mgcontent,type,id){
    var par={
        vehicleId: id,
        mgcontent: mgcontent,
        type: type
    };

    $.ajax({
        url:"/layImMsg/send",
        data:par,
        cache:false,
        type:'POST',
        success: function (result) {
                if(result.sendStatus=="true"){
                    if(type==2){
                        countDownTime(id);
                    }
                    var layim=layui.layim;
                    if(mgcontent.indexOf("face[")!=-1){
                        sendFace(mgcontent);
                        mgcontent="<img src="+face+">";
                    }
                    if(sendsFileType=="images"){
                       //  mgcontent='<div></div><div class="docs-galley">  <ul class="docs-pictures clearfix"> <li><img data-original="'+mgcontent+'"  src="'+mgcontent+'" alt="Cuo Na Lake"></li></ul></div></div>';
                       // mgcontent=' <div class="example" style="height: auto;width: 160px"><p><img class="img-rounded"  src="'+mgcontent+'"> </p></div><script type="text/javascript"> $(".example img").zoomify();</script>';
                       mgcontent='<img onclick="showsimg(this)" class="img-rounded" style="width:190px ;height:175px" src="'+mgcontent+'"/>';
                     //    mgcontent="<div onclick='showsimg()'><img class='img-rounded' style='width:160px ;height:175px' src="+mgcontent+"/></div>";
                    }/*
                     <div class="container"><div class="example col-xs-6 col-md-6">  <p><img class="img-rounded"  src="'+mgcontent+'"> </p></div></div>
                    */
                    if(sendsFileType=="file"){
                        if(contentsType=="video/mp4"||contentsType=="video/webm"||contentsType=="video/ogg"){
                            mgcontent="<div><video width='10px' height='13px' autoplay='autoplay' controls='controls'><source src="+mgcontent+"></video></div>";
                        }else if(contentsType=="audio/mpeg"||contentsType=="audio/ogg"||contentsType=="audio/wav"){
                            mgcontent= "<audio  controls='controls' src="+mgcontent+"/>"
                        }else{
                            mgcontent="<div style='text-align: center'><a href="+mgcontent+" download="+fileName+"><img src='/plugins/layui/images/file.jpg' style='width: 50px;height: 50px'><br>"+fileName+"</a></div>";
                        }
                    }
                    //监听发送消息
                    layim.getMessage({
                        username: "系统用户"
                        ,avatar: "/plugins/layui/images/command.png"
                        ,id:id
                        ,type: 'friend'
                        ,mine: true
                        ,content: mgcontent
                        ,timestamp:  (new Date(new Date().getTime())).format("yyyy-MM-dd hh:mm:ss")
                    });

                    document.getElementById("textareas"+id).value="";
                    document.getElementById("textareas"+id).focus();
                    beytLens(id);
                }else{
                    layer.msg(result.sendStatus);

                }
    }});

}
//平板电脑巡检查看详情
function jumpHandheldInspections(reportId){
    layer.open({
        type: 2,
        title: '平板巡检信息',
        shadeClose: true,
        area: [Medo.getPopupWidth(), Medo.getPopupHeight()],
        fixed: false, //不固定
        maxmin: true,
        offset: Medo.getClientHeight()/12,
        content: 'http://180.166.145.226:9080/webToApp/app/jsp/mdpage/mdisaster.jsp?userName=4850D31924C4582B&password=96E79218965EB72C92A549DD5A330112&reportId='+reportId //iframe的url
    });
}
//手持机巡检查看详情
function jumpFlatInspection(reportId){
    layer.open({
        type: 2,
        title: '手持机巡检信息',
        shadeClose: true,
        area: [Medo.getPopupWidth(), Medo.getPopupHeight()],
        fixed: false, //不固定
        maxmin: true,
        offset: Medo.getClientHeight()/12,
        content: 'http://180.166.145.226:9080/webToApp/app/jsp/mdpage/mreport.jsp?userName=4850D31924C4582B&password=96E79218965EB72C92A549DD5A330112&reportId='+reportId //iframe的url
    });
}
Date.prototype.format = function(format) {
    var o = {
        "M+": this.getMonth() + 1,
        // month
        "d+": this.getDate(),
        // day
        "h+": this.getHours(),
        // hour
        "m+": this.getMinutes(),
        // minute
        "s+": this.getSeconds(),
        // second
        "q+": Math.floor((this.getMonth() + 3) / 3),
        // quarter
        "S": this.getMilliseconds()
        // millisecond
    };
    if (/(y+)/.test(format) || /(Y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00"+ o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
};
function clearHistory(){
    for( var id in  deviceId){
        //清空聊天框缓存
        $("#chatLog"+id).html("<ul></ul>");
    }
    deviceId=[];
}



var msgcontent=null;
var contentsType=null;
var sendsFileType=null;
var fileName=null;

function laySendMsg(id){
      contentsType=null;
      sendsFileType=null;
      fileName=null;
    msgcontent = $("#textareas"+id).val();
    var type = $("#magesstypes"+id).val();
    send_msg(id,msgcontent,type);
}
$('html').keydown(function(event) {//,name,contentType,sendFileType
        if(liIndex==0){
            var codeNum = event.keyCode;
            if(codeNum == 13 && !event.ctrlKey){
                laySendMsg(msgid);
                return;
            }
        }else{
            if(event.keyCode == 13 && event.ctrlKey){
                laySendMsg(msgid);
                return;
            }
        }
    });
function commandFile(src,name,contentType,size,sendFileType){
    msgcontent=src;
    contentsType=contentType;
    sendsFileType=sendFileType;
    fileName=name;
    if(sendFileType=="images"){
        //页面层-自定义
        layer.open({
            resize:true,
                type: 1,
            title:"　　　　　图片预览发送",
            closeBtn: 0,
            shadeClose: true,
            skin: 'yourclass',
            content: "<html><head><title>图片预览发送</title></head><body><div style='text-align: center;width:260px;height: 310px;'><p>　</p><img style='width: 180px;height: 190px; ' src="+src+"><p>"+name+"</p><div class='layui-layer-btn layui-layer-btn-' style='text-align: center;'><a class='layui-layer-btn0' onclick='sendFile()' target='_blank'>发送</a> </div></div></body> </html>"
        });
    }else{
        layer.open({
            type: 1,
            closeBtn: 0,
            title:"　　　　　文件预览发送",
            shadeClose: true,
            skin: 'yourclass',
            content: "<html><head><title>文件预览发送</title></head><body><div style='text-align: center;width:260px;height: 280px;'><p>　</p><img style='width:130px;height: 140px;' src='/plugins/layui/images/file.jpg'><p>"+name+"</p><p style='color: #84909c'>"+size+"</p><div class='layui-layer-btn layui-layer-btn-' style='text-align: center;'><a class='layui-layer-btn0' onclick='sendFile()' target='_blank'>发送</a></div></div></body> </html>"
        });
    }
};

function sendFile(){
    var type = $("#magesstypes"+msgid).val();
    send_msg(msgid,msgcontent,type);
}
//加入监控
function addMonitor(id){
    var name=deviceType[id].username;
    var d= $("#map-bottom-panel");
    d.append(' <ul id="'+id+'"  class="list-group" ng-repeat="device in monitorDevices">  '+
    '<li class="list-group-item container-fluid">'+
    ' <div class="col-sm-3">'+
    '<a onclick="deleteMonitor('+id+')" title="移除监控" style="font-size:18px;" class="fa fa-times-circle"> </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="devicePositioning('+id+')"> '+name+'</a></div>'+
    '<div class="col-sm-3" id="'+id+'_panel"><a href="javascript:void(0);" onclick="devicePositioning('+id+')">当前坐标:</a></div>'+
    '<div class="col-sm-5" id="'+id+'_address"><a href="javascript:void(0);" onclick="devicePositioning('+id+')">当前地址:</a></div></li></ul>');
    addDeviceMonitor(id);//加入监控
}

//移除监控
function deleteMonitor(id){
    var ul=document.getElementById(id)
    ul.parentNode.removeChild(ul);
    delete monitorList[id];
    removeDeviceMonitor(id);
}

function showsimg(i){
  var url=$(i)[0].getAttribute("src");
    layer.open({
        maxWidth:'790px',
        type: 1,
        title: false,
        closeBtn: 0,
        area: 'auto',
        skin: 'layui-layer-nobg',
        shadeClose: true,
        content: '<img style="max-width:789px;" src='+url+'>'
    });
}
