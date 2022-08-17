<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create</title>

    <style>
        body {
            background-color: #EFEFEF;
        }

        #code_snippet {
             border: 2px solid #999797;
             background-color: white;
        }

        #send_snippet {
            border-radius: 3px;
            background-color: white;
        }
    </style>

    <script>
        function send() {
    let object = {
        "code": document.getElementById("code_snippet").value,
        "time": document.getElementById("time_restriction").value,
        "views": document.getElementById("views_restriction".value
    };

    let json = JSON.stringify(object);

    let xhr = new XMLHttpRequest();
    xhr.open("POST", '/api/code/new', false)
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xhr.send(json);

    if (xhr.status == 200) {
      alert("Success!");
    }
}
    </script>
</head>
<body>
    <textarea id="code_snippet">//write your code here</textarea>
    </br>
    <input id="time_restriction" type="text"/>
    </br>
    <input id="views_restriction" type="text"/>
    </br>
    <button id="send_snippet" type="submit" onclick="send()">Submit</button>
</body>
</html>