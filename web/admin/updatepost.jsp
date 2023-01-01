<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>NIC|BLOG</title>
    <%
        String context = request.getContextPath();
    %>
    <meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"
          name="viewport">
    <meta name="keywords" content="HTML,CSS,PHP,JavaScript,jQuery,XML,AJAX,,SQL,bootstrap,Python" />
    <meta name="description" content="K-smiles" />
    <link rel="shortcut icon" href="img/website.svg"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/manage.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/editormd/css/editormd.min.css">

    <link href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/blog.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/admin/editormd/editormd.min.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>

    <script type="text/javascript">
        $(function () {

            $.ajax({
                dataType: "json",    //数据类型为json格式
                type: "GET",
                url: "<%=context %>/servlet/CategoryServlet?action=ajaxmain",
                success: function (data, textStatus) {
                    var main_id = $("#main_id");
                    main_id.empty();
                    $.each(data, function (key, val) {
                        main_id.append('<option value="' + key + '">' + val + '</option>');
                    })

                },
                error: function (xhr, status, errMsg) {
                    alert("获取分类失败");
                }
            })
        });



        function beforeSubmit(form) {
            if (form.title.value == '') {
                alert('文章标题不能为空！');
                form.title.focus();
                return false;
            }
            if (form.test - editormd - markdown - doc.value == 0) {
                alert('文章内容不能为空！');
                form.category.focus();
                return false;
            }
            if (form.category.value == 0) {
                alert('文章类别不能为空！');
                form.category.focus();
                return false;
            }
            if (form.subtitle.value == '') {
                alert('文章摘要不能为空！');
                form.subtitle.focus();
                return false;
            }
            if (form.main_id.value == '') {
                alert('文章主分类不能为空！');
                form.subtitle.focus();
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div id="left-nav" class="col-md-2">
            <div class="author-nav">
                <img src="../img/103877375_p0_master1200.jpg" alt="个人头像">
            </div>
            <div class="main-nav">
                <ul>
                    <a href="<%=context %>/servlet/PostlistServlet?role=1">
                        <li>所有文章</li>
                    </a>
                    <a href="#">
                        <li class="current">写文章</li>
                    </a>
                    <a href="<%=context %>/servlet/CategoryServlet?action=getall">
                        <li>分类</li>
                    </a>

                    <a href="<%=context %>/index.html">
                        <li>返回首页</li>
                    </a>
                </ul>
            </div>
        </div>

        <form class="form-inline" action="<%=context %>/servlet/PosteditServlet?action=update" method="post"
              onSubmit="return beforeSubmit(this);">

            <div id="edit" class="col-md-8 col-xs-12">
                <h3>修改文章</h3>
                <hr/>
                <input type="text" id="article-title" name="title" class="form-control" placeholder=" ${result.dataList[0].title}"
                       required="true" autofocus="" autocomplete="off" style="width:100%;" value=" ${result.dataList[0].title}">
                <div class="editormd" id="test-editormd">
                    <textarea class="editormd-markdown-textarea" name="test-editormd-markdown-doc"
                              style="display: none;">
                        ${result.dataList[0].mdContent}
                    </textarea>

                </div>
                <script type="text/javascript">
                    $(function () {
                        editormd("test-editormd", {
                            width: "100%",
                            height: 650,
                            syncScrolling: "single",
                            //你的lib目录的路径，我这边用JSP做测试的
                            path: "../admin/editormd/lib/"
                        });
                    });
                </script>
            </div>
            <div id="operate" class="col-md-2 col-xs-12">
                <h3>操作</h3>
                <hr/>
                    <h5><input type="checkbox" class="input-control" name="top" value="1">&nbsp;将该文章置顶</h5>
                    <h4>发布时间：</h4>
                <div class="container">
                    <div class="form-group">

                        <div id="timeTest" class="input-group date form_datetime " data-date="2022-12-16"  data-link-field="dtp_input1">
                            <input class="form-control" class="input-group date form_datetime col-md-1" size="16" type="text" value="" readonly>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                        </div>
                        <input type="hidden" id="dtp_input1" name="timeDate" value="" /><br/>
                    </div>
                </div>
                <input type="hidden" name="id" value="${result.dataList[0].id} ">
                <h4>分类：</h4>
                <select class="form-control" id="main_id" name="main_id" style="margin:5px;"
                >

                </select>
                <button type="submit" class="btn btn-primary" style="float: right;margin:5px;">发布</button>
            </div>
        </form>
    </div>
</div>

<script type="text/javascript">
    $('#timeTest').datetimepicker({
        //language:  'fr',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 3,
        minView:2,
        forceParse: 0,
        showMeridian: 1,
        format:'yyyy-mm-dd '
    });

    $('#timeTest').datetimepicker().on('changeDate', function(ev){

        console.log($('#timeTest').datetimepicker('getUTCDate'))
        console.log($('#timeTest').datetimepicker('getDate'))


    });
</script>
</body>
</html>

