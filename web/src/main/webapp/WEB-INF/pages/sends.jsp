<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>jQuery Bootgrid Demo</title>
    <link href="/styles/bootstrap.css" rel="stylesheet" />
    <link href="/styles/jquery.bootgrid.css" rel="stylesheet" />
    <script src="/scripts/moderniz.2.8.1.js"></script>
    <style>
        @-webkit-viewport { width: device-width; }
        @-moz-viewport { width: device-width; }
        @-ms-viewport { width: device-width; }
        @-o-viewport { width: device-width; }
        @viewport { width: device-width; }

        body { padding-top: 70px; }

        .column .text { color: #f00 !important; }
        .cell { font-weight: bold; }
    </style>
</head>
<body>

<table id="grid-data" class="table table-condensed table-hover table-striped" data-toggle="bootgrid" data-ajax="true" data-url="list">
    <thead>
    <tr>
        <th data-column-id="ticket_no" data-type="string" data-identifier="true">ticket_no</th>
        <th data-column-id="title">title</th>
        <th data-column-id="parent_id" data-type="string">parent_id</th>
        <th data-column-id="contact_id" data-type="string">contact_id</th>
        <th data-column-id="status">status</th>
        <th data-column-id="priority" data-type="string">priority</th>
        <th data-column-id="smownerid" data-type="string">smownerid</th>
    </tr>
    </thead>

</table>

<footer id="footer">
    © Copyright 2014-2015, Rafael Staib
</footer>

<script src="/scripts/jquery-1.11.1.min.js"></script>
<script src="/scripts/bootstrap.js"></script>
<script src="/scripts/jquery.bootgrid.js"></script>
<script src="/scripts/jquery.bootgrid.fa.js"></script>
<script type="text/javascript">
    $("#grid").bootgrid({
        ajax: true,
        post: function ()
        {
            return {
                id: "b0df282a-0d67-40e5-8558-c9e93b7befed"
            };
        },
        url: "/user/list",
        selection: true,
        multiSelect: true,
        rowSelect: true,
        keepSelection: true,
        formatters: {
            "link": function(column, row)
            {
                return "<a href=\"#\">" + column.id + ": " + row.id + "</a>";
            }
        }
    })
    $("#some-button").on("click", function () {
        $("#grid-data").bootgrid("reload");
    });
</script>
</body>
</html>