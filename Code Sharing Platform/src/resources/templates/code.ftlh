<!DOCTYPE html>
<html lang="en">
<head>
    <title>Code</title>
    <link rel="stylesheet"
           target="_blank" target="_blank" target="_blank" target="_blank" target="_blank" href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
<pre id="code_snippet"><code>${code}</code></pre>
<span id="load_date">${date}</span>
<#if views?? >
<span id="views_restriction">${views} more views allowed </span>
</#if>
<#if time??>
<span id="time_restriction">The code will be available for ${time} seconds</span>
</#if>
</body>
</html>