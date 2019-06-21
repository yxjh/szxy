//页面刷新加载的接口
$(function () {
    dataLoad()
    title()
})
//公告接口
var title=function () {
    $.ajax({
        method:'GET',
        url : 'http://127.0.0.1:9999/index/indexAll',
        success: function (response) {
            if(response.responseCode == '1000') {
                for(var i = 0;i < response.content.title.length;i++){
                    var newLi='<li><span>【'+response.content.title[i].wplx+'】</span>'+response.content.title[i].wpms+'</li>'
                    $('.titleList').append(newLi)
                }
                }
            }
    })
}
//弹出留言框
function ly_div(data) {
    if(localStorage.getItem("xh")!=null){
        $('.lyk_userXh').val($(data)[0].attributes[1].value)
        $('.lyk_topicXh').val($(data)[0].attributes[2].value)
        $('.lyk').slideDown();
    }else{
        alert("亲，请先登录！！！");
    }
}
//添加留言
$('.dj_lyk').on('click',function () {
    ly();
})
//删除留言框
$('.del_lyk').on('click',function () {
    $('.lyk').slideUp();
})

//添加留言
var ly=function () {
    var user_xh=localStorage.getItem("xh");
    var topic_xh=$('.lyk_topicXh').val();
    var lynr=$('.lynr').val();
    var data={
        user_xh:user_xh,
        wp_xh:topic_xh,
        lynr:lynr,
    }
    $.ajax({
        method: 'GET',
        data:data,
        url: 'http://127.0.0.1:9999/index/addLy',
        success:function () {
            $('.lynr').val("");
            $('.lyk').slideUp();
        }
    })
}
//弹出查询框
function ck_div(data) {
    if(localStorage.getItem("xh")!=null){
        $('.user_xh').val($(data)[0].attributes[1].value)
        $('.topic_xh').val($(data)[0].attributes[2].value)
        $('.wp_detail').slideDown();
        detail();
    }else{
        alert("亲，请先登录！！！");
    }
}
//删除查询框
$('.del').on('click',function () {
    $('.wp_detail').slideUp();
})
//查询所有的上传物品接口
$('.submit').on('click',function () {
    $('.list').empty()
    var wplx = $('.wplx').val();
    if(wplx=="---请选择---"){
        wplx="";
    }
    var keys= $('.keys').val();
    var time= $('.ui-datepicker-time').val();
    dataLoad(wplx,keys,time,1,7);
})

var dataLoad = function (wplx,keys,beginTime,pageIndex,pageSize) {
    $('.list').empty();
    var data = {
        wplx : wplx,
        keys : keys,
        beginTime: beginTime,
        pageIndex : pageIndex,
        pageSize :pageSize
    };
    $.ajax({
        method:'GET',
        data :data,
        url : 'http://127.0.0.1:9999/index/findAll',
        success : function (response) {
            if(response.responseCode == '1000') {
                $('.num').text(response.content.total)
                if (response.content.list.length > 0) {
                    for(var i = 0;i < response.content.list.length;i++){
                        var newTr = '<tr>'
                            + '<td>' + response.content.list[i].rowId + '</td>'
                            + '<td>' + response.content.list[i].name + '</td>'
                            + '<td>' + response.content.list[i].scwp + '</td>'
                            + '<td>' + response.content.list[i].yxjhwp + '</td>'
                            + '<td>' + response.content.list[i].wplx + '</td>'
                            + '<td onclick="ck_div(this)" user_xh="'+response.content.list[i].userXh+'" topic_xh="'+response.content.list[i].wpXh+'" ><img src="index/img/ck.jpg" style="height: 55px;" class="ck_wp"></td>'
                            + '<td onclick="ly_div(this)" user_xh="'+response.content.list[i].userXh+'" topic_xh="'+response.content.list[i].wpXh+'"><img src="index/img/phone.jpg" style="height: 55px;cursor: pointer;" class="chat"></td>'
                            + '</tr>'
                        $('.list').append(newTr)
                    }
                }
                $('#page').paging({
                    pageNo: response.content.pageNum,
                    totalPage: response.content.pages,
                    totalSize: response.content.total,
                    callback: function(num) {
                        var wplx = $('.wplx').val();
                        if(wplx=="---请选择---"){
                            wplx="";
                        }
                        var keys= $('.keys').val();
                        var time= $('.ui-datepicker-time').val();
                        dataLoad(wplx,keys,time,num,7);
                    }
                })
            }
        }
    })
}

//物品详情
var detail=function () {
    var user_xh=$('.user_xh').val();
    var topic_xh=$('.topic_xh').val();
    var data={
        user_xh:user_xh,
        topic_xh:topic_xh
    }
    $.ajax({
        method: 'GET',
        data:data,
        url: 'http://127.0.0.1:9999/index/findByXh',
        success:function (response) {
            $('.user_name').text(response.content.name);
            $('.scwp').text(response.content.scwp);
            $('.yxjhwp').text(response.content.yxjhwp);
            $('.scwp').text(response.content.scwp);
            $('.scdd').text(response.content.scdd);
            $('.lx').text(response.content.wplx);
            $('.jyzt').text(response.content.jyzt);
            $('.scsj').text(response.content.scsj);
            $('.wpms').text(response.content.wpms)
            $(".img").attr('src',response.content.url);
        }
    })
}


