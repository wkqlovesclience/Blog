<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>访客分析</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/metro/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/wangEditor.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/echarts.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/china.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/datagrid-detailview.js"></script>
<script language="javascript" type="text/javascript">
        function yearChange(value){
            $.post("${pageContext.request.contextPath}/admin/visitor/getTrendChartStatistic.do?yearNum="+value,function(data){
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption({
                    xAxis: {
                        data: data.intervals
                    },
                    series: [{
                        itemStyle: {
                            normal: {
                                lineStyle: {
                                    color: '#3399ff'//控制折线颜色
                                }
                            }
                        },
                        markPoint: {
                            data: [
                                { type: 'max'},
                                { type: 'min'},
                            ]
                        },
                        markLine: {
                            data: [
                                { type: 'average'}//显示平均值
                            ]
                        },
                        // 根据名字对应到相应的系列
                        type: 'line',
                        name: '流量趋势',
                        data: data.count
                    }]
                });
            },"json");
            myChart.setOption(option);
        }

    </script>

</head>
<body>
    <span style="align-content: center">请选择年份：<select id="myYear" onchange="yearChange(this.value)"></select></span>
    <script type="text/javascript">
        window.onload=function(){
            //设置年份的选择
            var myDate= new Date();
            var startYear=myDate.getFullYear()-3;//起始年份
            var endYear=myDate.getFullYear();//结束年份
            var obj=document.getElementById('myYear')
            for (var i=startYear;i<=endYear;i++)
            {
                obj.options.add(new Option(i,i));
            }
            obj.options[obj.options.length-1].selected=1;
        }
    </script>



    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="statistics_main" style="width: 1200px;height: 500px;;margin-top: 30px;margin-left: 30px"></div>
 
</body>
<script type="text/javascript">
    var myChart = echarts.init(document.getElementById("statistics_main"));
    var option = {
        title: {
            text: '流量趋势图',
            subtext: '一年内访客统计'
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {//鼠标滑过的线条样式
                type: 'line',
                lineStyle: {
                    color: 'red',//颜色
                    width: 1,//宽度
                    type: 'solid'//实线
                }
            },
            formatter: function(value) {//鼠标滑过时显示内容的格式化
                var template = "";
                template += '月份：' + value[0].axisValue + "<br/>";
                template += '访客数量：' + value[0].data;
                return template;
            }
        },
        //右上角工具条
        toolbox: {
            show: true,
            feature: {
                mark: { show: true },//目前还不知道有啥用
                dataView: { show: true, readOnly: true ,title:'data view'},//数据视图
                magicType: { show: true, type: ['line', 'bar'],title:['line', 'bar'] },//折线与柱状的切换
                restore: { show: true ,title:'revert'},//重置
                saveAsImage: { show: true ,title:'save'}//保存为图片
            }
        },
        calculable: true,
        xAxis: [{//x轴的数据
            type: 'category',
            name:'月份',
            boundaryGap: false,//若为true,则x轴的值不在刻度上.
            data: [],
            axisLabel: {//y轴的内容格式化,很有用的属性
                formatter: '{value}'
            }
        }],
        yAxis: [{
            type: 'value',
            name: "流量趋势",
            axisLabel: {//y轴的内容格式化,很有用的属性
                formatter: '{value}'
            }
        }],
        legend: {
            data: ['流量趋势']//要与series中的name一致
        },

    };

    $.post("${pageContext.request.contextPath}/admin/visitor/getTrendChartStatistic.do",function(data){
        console.log(data);
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption({
            xAxis: {
                data: data.intervals
            },
            series: [{
                itemStyle: {
                    normal: {
                        lineStyle: {
                            color: '#3399ff'//控制折线颜色
                        }
                    }
                },
                markPoint: {
                    data: [
                        { type: 'max'},
                        { type: 'min'},
                    ]
                },
                markLine: {
                    data: [
                        { type: 'average'}//显示平均值
                    ]
                },
                // 根据名字对应到相应的系列
                type: 'line',
                name: '流量趋势',
                data: data.count
            }]
        });
    },"json");
    myChart.setOption(option);


    $(function () {
        $.post("${pageContext.request.contextPath}/admin/visitor/getTrendChartStatistic.do", function (data) {
            console.log(data);
            myChart.setOption({
                series: [{
                    // 根据名字对应到相应的系列
                    name: '流量趋势',
                    data: data
                }]
            });
        }, "json");
    });
    myChart.setOption(option);
</script>
</html>