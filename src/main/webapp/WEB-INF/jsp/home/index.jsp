<%@ include file="../common/header.jspf" %>
<body class="hold-transition sidebar-mini">

<div class="wrapper">
    <%@ include file="../common/navigation.jspf" %>
    <%@ include file="../common/sidebar.jsp" %>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1>DataTables</h1>
                    </div>
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">Home</a></li>
                            <li class="breadcrumb-item active">Scaffolding</li>
                        </ol>
                    </div>
                </div>
            </div><!-- /.container-fluid -->
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-header">
                                <h3 class="card-title">Welcome</h3>
                            </div>
                            <!-- /.card-header -->
                            <div class="card-body">
                                <button id="submit" type="submit" class="btn btn-primary">Submit</button>
                            </div>
                            <!-- /.card-body -->
                        </div>
                        <!-- /.card -->
                    </div>
                    <!-- /.col -->
                </div>
                <!-- /.row -->
            </div>
            <!-- /.container-fluid -->
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
    <%@ include file="../common/footer.jspf" %>
</div>
<!-- ./wrapper -->
<script type="text/javascript">
    function showOverLay(message) {
        var html = ''
        html += '<div id="processing-overlay" class="overlay"><i class="fas fa-2x fa-sync-alt fa-spin"></i>' + "&nbsp;" + message + '</div>'
        return html
    }

    $(document).on("click", "#submit", function () {
        $(".card").append(showOverLay("Opening calculator"));
        $.when(showCalculator()).done(function (data) {
            alert("Result after calculation: "+data.output);
            $(".card").find("#processing-overlay").remove();
        })
    });

    function showCalculator(url) {
        return $.ajax({
            type: 'GET',
            url: "/showCalculator",
            data: {},
        });
    }

</script>
