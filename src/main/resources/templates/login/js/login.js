//注册
$('#create').on('click',function () {
    var name=$('#r_user_name').val();
    var password=$('#r_password').val();
    var email=$('#r_emial').val();
    $.ajax({
        method:'GET',
        data:{
            name:name,
            password:password,
            email:email,
        },
        url:'http://127.0.0.1:9999/user/register',
        success:function (response) {
            $.ajax({
                method: 'GET',
                data:{
                    name:name,
                    password:password
                },
                url: 'http://127.0.0.1:9999/user/login',
                success:function (response) {
                    var xh=response.content.xh;
                    var name=response.content.name;
                    localStorage.setItem("xh",xh);
                    localStorage.setItem("name",name);
                    window.location.href = "index.html";
                }
            })
        }
    })
})
//登陆
$('#login').on('click',function () {
    var name=$('#user_name').val();
    var password=$('#password').val();
    $.ajax({
        method: 'GET',
        data:{
            name:name,
            password:password
        },
        url: 'http://127.0.0.1:9999/user/login',
        success:function (response) {
            if(response.content!=null){
                var xh=response.content.xh;
                var name=response.content.name;
                localStorage.setItem("xh",xh);
                localStorage.setItem("name",name);
                window.location.href = "index.html";
            }else {
                $('#user_name').val("");
                $('#password').val("");
                localStorage.setItem("name","");
                $('.msg').text('用户名或密码错误，请重新登陆')
            }
        }
    })
})