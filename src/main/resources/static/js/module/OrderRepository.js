let baseUrl = window.location.origin;
export default class OderRepository{

    findPromise(url,method="GET", data = null){

        let header = {
            "Content-Type": "application/json",
        }

        // 메소드가 GET이 아니라면 option 넣음
        let option = method === 'GET' ? null : {method:method, headers:header, body:data}

        return fetch(url, option);

    }

    async findAll(query,sortType){
        let url = `${baseUrl}/api/order?`;

        if(query)
            url += `q=${query}&`

        if(sortType)
            url += `s=${sortType}`

        let response = await this.findPromise(url);
        return await response.json();
    }

    async findAllByOption(idx,query,sortType){

        let option = {
            2:'exch',   //교환
            3:'rfnd',   //환불
            4:'cncl'    //취소
        }

        let url = `${baseUrl}/api/order/${option[idx]}`;

        if(query)
            url += `q=${query}&`

        if(sortType)
            url += `s=${sortType}`

        let response = await this.findPromise(url);
        return await response.json();
    }

}

