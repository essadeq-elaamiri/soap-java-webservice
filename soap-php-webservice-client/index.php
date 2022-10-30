<?php 
    $amout = 0;
    $result = 0;
    $WSDL = "http://localhost:9292/BankWS/?wsdl";
    if(isset($_POST["amount"])){
        $amout = $_POST["amount"];
        
        $client_soap = new SoapClient($WSDL);

        $param = new stdClass(); // standard class
        $param->amount=$amout; //adding param
        $res = $client_soap->__soapCall("covert", [$param]/*args*/); // call a soap function
        $result = $res->return; //  access the return attribute of the soap object returned
    }
    
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <form method="post">
        <table style="width: 50% ; margin:auto;">
            <tr>
                <td>Amount:</td>
                <td>
                    <input type="number" name="amount" style="width: 100% ;">
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <input type="submit" value="SUBMIT" style="width: 100% ;">
                </td>
            </tr>
            <tr>
                <td>Result:</td>
                <td>
                    <div style="background-color: #aaa ; padding:10px;">
                        <?=$result?> <!---->
                    </div>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>