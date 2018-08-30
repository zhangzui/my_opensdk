<html>
<body>
<h2>Hello World!</h2>
<p>
    @RequestMapping(value = "/gateway1")
    @ResponseBody
    public OpenAPIEntity execute1(OpenAPIEntity openAPIEntity) {
    return transactionService.transactionService(openAPIEntity);
    }
</p>
<button id="buttons001">gateway1</button>
<button id="buttons002">gateway2</button>
</body>
</html>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/index.js"></script>
<script>
    $("#001").html("")
</script>

