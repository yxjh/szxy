setInterval(function () {
    time();
},1000);
//获取时间
function time(){
    var myDate = new Date();
    var year = myDate.getFullYear();//获取当前年
    var yue = myDate.getMonth()+1;//获取当前月
    var date = myDate.getDate();//获取当前日
    var h = myDate.getHours();//获取当前小时数(0-23)
    var m = myDate.getMinutes();//获取当前分钟数(0-59)
    var s = myDate.getSeconds();//获取当前秒
    if(yue<10){
        yue = "0"+yue;
    }
    if(date<10){
        date= "0"+ date;
    }
    if(h<10){
        h="0"+h;
    }
    if(s<10){
        s="0"+s;
    }
    if(m<10){
        m="0"+m;
    }
    $(".topTime").html(h + ":" + m + ":" + s);
    $(".topYear").html(year + "年" + yue + "月" + date + "日");
    return year+"-"+yue+"-"+date;
};

//用户退出
$('.exit').on("click",function () {
     localStorage.clear();
     window.location.href="login.html";
})