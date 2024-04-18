const header = document.querySelector("header");
const cartCircle = header.querySelector(".cart-circle");

export default function addCount(count) {
    cartCircle.classList.remove("d:none")
    cartCircle.textContent = count;
}

class HeaderCart {
    #header
    #cartCircle
    constructor() {
        this.#header = document.querySelector("header");
        this.#cartCircle = header.querySelector(".cart-circle");
        console.log("들어옴");
    }
}