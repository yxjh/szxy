$(function () {
    $('.ui-datepicker-time').val("");
    complete();
    totalPercent();
})
var obj = {
    shyp:[0,0,0,0,0,0,0],
    ylyp:[0,0,0,0,0,0,0],
    xxyp:[0,0,0,0,0,0,0]
}


//轮播开始
$('.faceImg').html('');
for(var i = 0;i < 5;i++){
    var newDiv = '<div class="faceImgDiv"><span><img src="index/img/demo_'+ (i+1) +'.jpg" alt="'+ (i + 1) +'" onerror="nofind(this)" onclick="openImg(this)" title="'+ (i + 1) +'"></span><p class="imgDescribtion">描述'+ i +'</p></div>';
    $('.faceImg').append(newDiv);
}
$('.faceImg').vmcSlider({
    width: $('.faceImg').width(),
    height: $('.faceImg').height(),
    gridCol: 100,
    gridRow: 5,
    gridVertical: 20,
    gridHorizontal: 10,
    autoPlay: true,
    ascending: true,
    effects: [
        'fade', 'fadeLeft', 'fadeRight', 'fadeTop', 'fadeBottom', 'fadeTopLeft', 'fadeBottomRight',
        'blindsLeft', 'blindsRight', 'blindsTop', 'blindsBottom', 'blindsTopLeft', 'blindsBottomRight',
        'curtainLeft', 'curtainRight', 'interlaceLeft', 'interlaceRight', 'mosaic', 'bomb', 'fumes'
    ],
    ie6Tidy: false,
    random: false,
    duration: 5000,
    speed: 900
});
//图片放大
function openImg(item){
    layer.photos({
        photos: '.slides'
        ,anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
    });
}

var totalPercent=function () {
    $.ajax({
        method:'POST',
        url: 'http://127.0.0.1:9999/index/totalPercent',
        success:function (response) {
            if(response.content.day_1){
                response.content.day_1.forEach(function (item) {
                    if(item.wplx =="学习用品"){
                        obj.xxyp[0] = item.amount
                    }else if(item.wplx =="生活用品"){
                        obj.shyp[0] = item.amount
                    }else if(item.wplx =="娱乐用品"){
                        obj.ylyp[0] = item.amount
                    }
                })
            }
            if(response.content.day_2){
                response.content.day_2.forEach(function (item) {
                    if(item.wplx =="学习用品"){
                        obj.xxyp[1] = item.amount
                    }else if(item.wplx =="生活用品"){
                        obj.shyp[1] = item.amount
                    }else if(item.wplx =="娱乐用品"){
                        obj.ylyp[1] = item.amount
                    }
                })
            }
            if(response.content.day_3){
                response.content.day_3.forEach(function (item) {
                    if(item.wplx =="学习用品"){
                        obj.xxyp[2] = item.amount
                    }else if(item.wplx =="生活用品"){
                        obj.shyp[2] = item.amount
                    }else if(item.wplx =="娱乐用品"){
                        obj.ylyp[2] = item.amount
                    }
                })
            }

            if(response.content.day_4){
                response.content.day_4.forEach(function (item) {
                    if(item.wplx =="学习用品"){
                        obj.xxyp[3] = item.amount
                    }else if(item.wplx =="生活用品"){
                        obj.shyp[3] = item.amount
                    }else if(item.wplx =="娱乐用品"){
                        obj.ylyp[3] = item.amount
                    }
                })
            }
            if(response.content.day_5){
                response.content.day_5.forEach(function (item) {
                    if(item.wplx =="学习用品"){
                        obj.xxyp[4] = item.amount
                    }else if(item.wplx =="生活用品"){
                        obj.shyp[4] = item.amount
                    }else if(item.wplx =="娱乐用品"){
                        obj.ylyp[4] = item.amount
                    }
                })
            }
            if(response.content.day_6){
                response.content.day_6.forEach(function (item) {
                    if(item.wplx =="学习用品"){
                        obj.xxyp[5] = item.amount
                    }else if(item.wplx =="生活用品"){
                        obj.shyp[5] = item.amount
                    }else if(item.wplx =="娱乐用品"){
                        obj.ylyp[5] = item.amount
                    }
                })
            }
            if(response.content.day_7){
                response.content.day_7.forEach(function (item) {
                    if(item.wplx =="学习用品"){
                        obj.xxyp[6] = item.amount
                    }else if(item.wplx =="生活用品"){
                        obj.shyp[6] = item.amount
                    }else if(item.wplx =="娱乐用品"){
                        obj.ylyp[6] = item.amount
                    }
                })
            }
            option_wp(obj);
        }
    })
}
//折线图
function option_wp(obj) {
    console.log(obj)
    var myChart1 = echarts.init(document.getElementById('main1'));
// 指定图表的配置项和数据
    option = {
        title: {
            text: '闲置物品统计'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['生活用品','娱乐用品','学习用品']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: ['周一','周二','周三','周四','周五','周六','周日']
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name:'生活用品',
                type:'line',
                // stack: '总量',
                data:obj.shyp,
                smooth: true
            },
            {
                name:'娱乐用品',
                type:'line',
                // stack: '总量',
                data:obj.ylyp,
                smooth: true
            },
            {
                name:'学习用品',
                type:'line',
                // stack: '总量',
                data:obj.xxyp,
                smooth: true
            }
        ]
    };
// 使用刚指定的配置项和数据显示图表。
    myChart1.setOption(option);
}



//饼图接口
var complete=function () {
    $.ajax({
        method: 'GET',
        url: 'http://127.0.0.1:9999/index/complete',
        success:function (response) {
            var wjy = response.content[0].amount;
            var jyz = response.content[1].amount;
            var jyw = response.content[2].amount;
            //饼图
            var myChart2=echarts.init(document.getElementById('main2'));
            option = {
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b}: {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    x: 'left',
                    data:['未交易','交易中','已交易']
                },
                series: [
                    {
                        name:'宿州学院闲置物品商城库',
                        type:'pie',
                        radius: ['50%', '70%'],
                        avoidLabelOverlap: false,
                        label: {
                            normal: {
                                show: false,
                                position: 'center'
                            },
                            emphasis: {
                                show: true,
                                textStyle: {
                                    fontSize: '30',
                                    fontWeight: 'bold'
                                }
                            }
                        },
                        labelLine: {
                            normal: {
                                show: false
                            }
                        },
                        data:[
                            {value:wjy, name:'未交易'},
                            {value:jyz, name:'交易中'},
                            {value:jyw, name:'已交易'},
                        ]
                    }
                ]
            };
            myChart2.setOption(option);
        }
    })
}


