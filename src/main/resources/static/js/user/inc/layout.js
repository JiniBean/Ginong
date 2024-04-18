import addCount from "./header.js";
import CartRepository from "../../module/CartRepository.js";

document.addEventListener("load", function (){
   // 로그인 완성 후 삭제
    let memberId = 2;
console.log("들어옴");
addCount(5);
    // if(memberId != null){
    //     let cartRepository =  new CartRepository();
    //     // let count = await cartRepository.count();
    //
    //     console.log("count : " , count);
    //     if (count > 0)
    //         addCount(count);
    // }


})