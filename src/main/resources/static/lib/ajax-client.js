let ajaxClient = (() => {
    async function getData(url) {
        return new Promise(async function (resolve, reject) {
            await fetch(getCompleteUrl(url))
                .then(response => response.json())
                .then(data => resolve(data))
                .catch(error => reject(error));
        });
    }

    async function postData(url, data) {
        const headers = new Headers();
        headers.append("Content-Type", "application/json");

        return new Promise(async function (resolve, reject) {
            await fetch(getCompleteUrl(url), {
                method: "POST",
                body: JSON.stringify(data),
                headers: headers,
            })
                .then(response => response.json())
                .then(success => resolve(success))
                .catch(error => reject(error));
        });
    }

    async function deleteData(url) {
        return new Promise(async function (resolve, reject) {
            await fetch(getCompleteUrl(url), {
                method: "DELETE",
            })
                .then(response => response.json())
                .then(success => resolve(success))
                .catch(error => reject(error));
        });
    }

    function getCompleteUrl(url) {
        if (url.startsWith('/')) url = url.substring(1);
        return `${getApplicationContextPath()}${url}`;
    }

    function getApplicationContextPath() {
        return document.getElementById('application-context-path').href; /*e.g, http://localhost:8080/*/
    }

    return {
        getData,
        postData,
        deleteData
    }
})();