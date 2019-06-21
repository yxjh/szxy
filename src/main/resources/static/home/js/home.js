$(function () {
    myWp('');
    myLy();
    homeNum();
    myTopic();
    myReply();
    myInfo();
    $('.num1').text(localStorage.getItem("name"));
})

//点击我的闲置物品
$('#info2').on('click',function () {
    $('.right').load("myWp.html");
    window.location.reload();
})

//点击我的个人信息
$('#info1').on('click',function () {
    $('.right').load("myInfo.html");
})

//点击修改我的个人信息
$('.edit').on('click',function () {
    editMyInfo();
})

//点击我的主题
$('#info3').on('click',function () {
    $('.right').load("myTopic.html");
})

//点击我的回帖
$('#info4').on('click',function () {
    $('.right').load("myReply.html");
})
//点击我的留言
$('#info5').on('click',function () {
    $('.right').load("myLy.html");
})
//头部数字统计
var homeNum=function () {
    var xh=localStorage.getItem("xh");
    $.ajax({
        method: 'GET',
        url: 'http://127.0.0.1:9999/home/headNum',
        data:{
            xh:xh,
        },
        success:function (response) {
            if(response.responseCode=='1000'){
                $('.num2').text(response.content.zt)
                $('.num3').text(response.content.ht)
            }
        }
    })
}

//我的回帖
var myReply=function () {
    var xh=localStorage.getItem("xh");
    $.ajax({
        method:'GET',
        data:{
            xh:xh,
        },
        url: 'http://127.0.0.1:9999/home/myReply',
        success:function (response) {
            $('.replyList').html("");
            if(response.responseCode == '1000') {
                if (response.content.length > 0) {
                    for(var i = 0;i < response.content.length;i++){
                        var newTr = '<tr>'
                            + '<td>' + response.content[i].rownum +'</td>'
                            + '<td style="width: 60px">' + response.content[i].ftr + '</td>'
                            + '<td>' + response.content[i].ftnr + '</td>'
                            + '<td>' + response.content[i].ftsj + '</td>'
                            + '<td>' + response.content[i].htr + '</td>'
                            + '<td>' + response.content[i].htnr + '</td>'
                            + '<td>' + response.content[i].htsj + '</td>'
                            + '</tr>'
                        $('.replyList').append(newTr)
                    }
                }
            }
        }
    })
}

function updateState(data) {
    var topic_xh=$(data)[0].attributes[1].value;
    $.ajax({
        method:'GET',
        data:{
            topic_xh:topic_xh,
        },
        url:'http://127.0.0.1:9999/home/updateState',
        success:function (response) {
            alert("确定交易.......");
        }
    })
}
//我的留言
var myLy=function () {
    var xh=localStorage.getItem("xh");
    $.ajax({
        method:'GET',
        data:{
            xh:xh,
        },
        url: 'http://127.0.0.1:9999/home/myLy',
        success:function (response) {
            $('.toLyList').html("");
            $('.fromLyList').html("");
            if(response.responseCode == '1000') {
                if (response.content.to.length > 0) {
                    for(var i=0;i<response.content.to.length;i++){
                        var newTr = '<tr>'
                            + '<td>' + response.content.to[i].rownum +'</td>'
                            + '<td style="width:200px;margin-left: 100px">' + response.content.to[i].ftr + '</td>'
                            + '<td style="width:200px;margin-left: 100px">' + response.content.to[i].scwp + '</td>'
                            + '<td style="width:400px;margin-left: 100px">' + response.content.to[i].yxjhwp + '</td>'
                            + '<td style="width:200px;margin-left: 100px">' + response.content.to[i].lyr + '</td>'
                            + '<td style="width:200px;margin-left: 100px">' + response.content.to[i].lysj + '</td>'
                            + '<td onclick="updateState(this)" topic_xh="'+response.content.to[i].xh+'" style="width:200px;margin-left: 100px;cursor: pointer"><img src="home/img/ok1.png"></td>'
                            + '</tr>'
                        $('.toLyList').append(newTr)
                    }
                }
                if (response.content.from.length > 0) {
                    for(var i=0;i<response.content.from.length;i++){
                        var newTr = '<tr>'
                            + '<td>' + response.content.from[i].rownum +'</td>'
                            + '<td style="width:220px">' + response.content.from[i].ftr + '</td>'
                            + '<td style="width:210px;margin-left: 200px">' + response.content.from[i].scwp + '</td>'
                            + '<td style="width:270px;margin-left: 200px">' + response.content.from[i].yxjhwp + '</td>'
                            + '<td style="width:270px;margin-left: 500px">' + response.content.from[i].lyr + '</td>'
                            + '<td>' + response.content.from[i].lysj + '</td>'
                            + '</tr>'
                        $('.fromLyList').append(newTr)
                    }
                }
            }
        }
    })
}

//我的主题
var myTopic=function () {
    var xh=localStorage.getItem("xh");
    $.ajax({
        method:'GET',
        data:{
            xh:xh,
        },
        url: 'http://127.0.0.1:9999/home/myTopic',
        success:function (response) {
            $('.TopicList').html("");
            if(response.responseCode == '1000') {
                if (response.content.length > 0) {
                    for(var i = 0;i < response.content.length;i++){
                        var newTr = '<tr>'
                            + '<td>' + response.content[i].rownum +'</td>'
                            + '<td style="width: 740px">' + response.content[i].ftnr + '</td>'
                            + '<td style="width:50px">' + response.content[i].zhhtr + '</td>'
                            + '<td style="width: 220px">' + response.content[i].htl + '</td>'
                            + '<td style="width:270px">' + response.content[i].ftsj + '</td>'
                            + '</tr>'
                        $('.TopicList').append(newTr)
                    }
                }
            }
        }
    })
}

//我的个人信息
var myInfo=function () {
    var xh=localStorage.getItem("xh");
    $.ajax({
        method:'GET',
        data:{
            xh:xh,
        },
        url: 'http://127.0.0.1:9999/home/myInfo',
        success:function (response) {
            if(response.responseCode == '1000') {
                $('.myName1').text(response.content.name);
                $('.myBirthday').val(response.content.birthday);
                $('.myEmail').val(response.content.email);
                $('.myBirthday1').text(response.content.birthday);
                $('.myName').val(response.content.name);
                $('.myNc').val(response.content.nc);
                $('.myPassword').val(response.content.password);
                $('.mytp').attr('src',response.content.url);
            }
        }
    })
}

//编辑我的个人信息
var editMyInfo=function () {
    var xh = localStorage.getItem("xh");
    $('.myXh').val(xh);
    var formData = new FormData(document.getElementById("form1"));
    $.ajax({
        method:'POST',
        data:formData,
        url:'http://127.0.0.1:9999/home/editMyInfo',
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success:function () {
            alert("个人信息修改成功！！！");
            myInfo()
        }
    })
}

//我上传的物品
var myWp=function () {
    var xh=localStorage.getItem("xh");
    $.ajax({
        method:'GET',
        data:{
            xh:xh,
        },
        url: 'http://127.0.0.1:9999/home/myWp',
        success:function (response) {
            console.log(response)
            if(response.responseCode == '1000') {
                if (response.content.length > 0) {
                    for(var i = 0;i < response.content.length;i++){
                        var newTr = '<tr>'
                            + '<td>' + response.content[i].rownum +'</td>'
                            + '<td>' + response.content[i].scwp + '</td>'
                            + '<td style="width:450px">' + response.content[i].wpms + '</td>'
                            + '<td>' + response.content[i].wplx + '</td>'
                            + '<td style="width:170px">' + response.content[i].yxjhwp + '</td>'
                            + '<td  style="width:100px">' + response.content[i].scdd + '</td>'
                            + '<td style="width:170px;">' + response.content[i].jyzt + '</td>'
                            + '<td>' + response.content[i].scsj + '</td>'
                            + '</tr>'
                        $('.homeList').append(newTr)
                    }
                }
            }
        }
    })
}

//导入excel表
$('.excel').on('mouseover',function () {
    var xh=localStorage.getItem("xh");
    $(this).attr('href',"http://127.0.0.1:9999/home/excelMyWp?xh="+xh)
})

//弹出物品上传框
$('.upload').on('click',function () {
    $('.wp_detail').slideDown();
    $('#form2').reset();
})
//删除物品上传框
$('.del').on('click',function () {
    $('.wp_detail').slideUp();

})

//物品上传
$('.wp_button').on('click',function () {
    var user_xh=localStorage.getItem("xh");
    $('.xh').val(user_xh);
    var formData = new FormData(document.getElementById("form2"));
    $.ajax({
        method:'POST',
        url:'http://127.0.0.1:9999/home/upload',
        data: formData,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success:function () {
            $('.wp_detail').slideUp(100);
        }
    })
})
