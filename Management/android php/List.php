<?php        
    $con = mysqli_connect("localhost", "jay1103", "moiaisarang1!", "jay1103");
    // 모든 유저를 모두 뽑아오는 역할을 한다.
    $result = mysqli_query($con, "SELECT * FROM USER;");
    $response = array();

    while($row = mysqli_fetch_array($result)) {
        array_push($response, array("userID"=>$row[0], "userPassword"=>$row[1], "userName"=>$row[2], "userAge"=>$row[3]));
    }

    // 유저 아이디, 유저 패스워드, 유저 이름, 유저 나이 순으로 뽑아와서 response라고 이름을 붙여서 돌려준다.
    echo json_encode(array("response"=>$response));

    mysqli_close($con);
?>