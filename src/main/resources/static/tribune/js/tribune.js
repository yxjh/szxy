$(function () {
    num()
    allTopic('')
    $('.head').load("top.html")
})
//头部数字统计
var num=function () {
    $.getJSON('http://127.0.0.1:9999/topic/num',function (response) {
        if(response.responseCode=='1000'){
            $('.num1').text(response.content.today)
            $('.num2').text(response.content.yesterday)
            $('.num3').text(response.content.total)
        }
    })
}
//发帖的接口
$('.topic').on('click',function () {
    var xh=localStorage.getItem("xh");
    if(xh!=null){
        var ftnr = $('.area').val();
        var user_xh= localStorage.getItem("xh");
        if(ftnr !=null && ftnr !=''){
            $.ajax({
                method:'GET',
                data:{
                    ftnr:ftnr,
                    user_xh:user_xh
                },
                url : 'http://127.0.0.1:9999/topic/startTopic',
                success:function () {
                    num();
                    allTopic('');
                }
            })
            $('.area').val('')
        }
    }else{
        alert("亲，您还没登陆呢");
    }
})
//回帖的接口
$('.ht').on('click',function () {
    var htnr=$('.htnr').val();
    var user_xh=localStorage.getItem("xh");
    var topic_xh=$('.ycxh').text();
    if(htnr !=null && htnr !=''){
        $.ajax({
            method:'GET',
            data:{
                htnr:htnr,
                user_xh:user_xh,
                topic_xh:topic_xh
            },
            url : 'http://127.0.0.1:9999/topic/reply',
            success:function () {
                $('.htk').slideUp();
                num();
                allTopic('');
            }
        })
        $('.htnr').val('')
    }
})
//弹出回帖框
function ht(data) {
    var xh=localStorage.getItem("xh");
    if(xh!=null){
        $('.htk').slideDown();
        $('.ycxh').text($(data).children().text());
    }else{
        //alert("亲，请先登录!!!!")
    }
}

//删除回帖框
$('.del').on('click',function () {
    $('.htk').slideUp();
})

$('.searchReply').on('click',function () {
    var key= $('.topicKey').val();
    allTopic(key,1,15);
})

//全部主题帖的接口
var allTopic=function (key,pageIndex,pageSize) {
    pageSize=15;
    $('.list').empty();
    var data = {
        key : key,
        pageIndex : pageIndex,
        pageSize :pageSize,
    };
    $.ajax({
        method: 'GET',
        data: data,
        url: 'http://127.0.0.1:9999/topic/allTopic',
        success:function (response) {
            if(response.responseCode == '1000') {
                $('.num').text(response.content.total)
                if (response.content.list.length > 0) {
                    for(var i = 0;i < response.content.list.length;i++){
                        var newTr = '<tr>'
                            + '<td>' + response.content.list[i].rowId +'</td>'
                            + '<td>' + response.content.list[i].ftnr + '</td>'
                            + '<td>' + response.content.list[i].name + '</td>'
                            + '<td>' + response.content.list[i].htl + '</td>'
                            + '<td>' + response.content.list[i].zhhtr + '</td>'
                            + '<td class="reply" onclick="ht(this)"><span class="topic_xh" style="display: none">'+response.content.list[i].xh +'</span>回帖</td>'
                            + '</tr>'
                        $('.list').append(newTr)
                    }
                }
                $("#page").paging({
                    pageNo: response.content.pageNum,
                    totalPage: response.content.pages,
                    totalSize: response.content.total,
                    callback: function(num) {
                        var key= $('.topicKey').val();
                        allTopic(key,num,7)
                    }
                })
            }
        }
    })
    num()
}

