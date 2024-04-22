let baseUrl = window.location.origin;
export default class CartRepository{

    findPromise(url,method="GET", data){
        return fetch(url,{method:method});
        // return fetch(url,{method:method, body:JSON.stringify(data)});

    }

    async findItem(prdId){
        let url = `${baseUrl}/user/api/cart/${prdId}`;

        let response = await this.findPromise(url);

        // 응답의 상태 코드 확인
        if (response.status === 200) {
            //response가 비었는지 확인
            if (response.headers.get('content-length') === '0') {
                return null;
            }
            let item = await response.json();
            return item;
        }
    }

    async count(){
        let url = `${baseUrl}/user/api/cart/c`;

        let response = await this.findPromise(url);

        // 응답의 상태 코드 확인
        if (response.status === 200) {
            //response가 비었는지 확인
            if (response.headers.get('content-length') === '0') {
                return null;
            }
            let count = await response.json();
            return count;
        }
    }


    async add(prdId){
        let url = `${baseUrl}/user/api/cart/a/${prdId}`;
        // let method = 'POST';
        // let data = {"prdId" : prdId};
        // let response = await this.findPromise(url,method, data);
        let response = await this.findPromise(url);
        return await response.json();
    }

    async addQty(prdId){
        let url = `${baseUrl}/user/api/cart/a/${prdId}?q=1`;
        // let method = 'POST';
        // let data = {"prdId" : prdId};
        // let response = await this.findPromise(url,method, data);
        let response = await this.findPromise(url);
        return await response.json();
    }




}

