<!DOCTYPE html>
<html lang="en">
<head>
    <title>Latest</title>
    <link rel="stylesheet"
           target="_blank" target="_blank" target="_blank" target="_blank" target="_blank" href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
    <#list codes as code>
        <pre id="code_snippet"><code>${code.code}</code></pre>
        <span id="load_date">${code.date}</span>
    </#list>
</body>
</html>