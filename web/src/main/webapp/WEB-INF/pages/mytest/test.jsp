<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>


</head>
<body>
<p><i class="icon-camera-retro icon-large"></i> icon-camera-retro</p>
<table id="grid-data" class="table table-condensed table-hover table-striped">
    <thead>
    <tr>
        <th data-column-id="id" data-type="numeric">ID</th>
        <th data-column-id="usrName">Sender</th>
        <th data-column-id="commands" data-formatter="commands" data-sortable="false">Commands</th>
    </tr>
    </thead>
</table>

<script type="text/javascript">
    var grid = $("#grid-data").bootgrid({
        ajax: true,
        post: function ()
        {
            return {
                id: "b0df282a-0d67-40e5-8558-c9e93b7befed"
            };
        },
        url: "/user/list",
        formatters: {
            "commands": function(column, row)
            {
                return "<button type=\"button\" class=\"btn btn-xs btn-default command-edit\" data-row-id=\"" + row.id + "\"><span class=\"icon-edit\"></span></button> " +
                        "<button type=\"button\" class=\"btn btn-xs btn-default command-delete\" data-row-id=\"" + row.id + "\"><span class=\"fa fa-trash-o\"></span></button>";
            }
        }
    }).on("loaded.rs.jquery.bootgrid", function()
    {
        /* Executes after data is loaded and rendered */
        grid.find(".command-edit").on("click", function(e)
        {
            alert("You pressed edit on row: " + $(this).data("row-id"));
        }).end().find(".command-delete").on("click", function(e)
        {
            alert("You pressed delete on row: " + $(this).data("row-id"));
        });
    });
</script>
</body>
</html>

