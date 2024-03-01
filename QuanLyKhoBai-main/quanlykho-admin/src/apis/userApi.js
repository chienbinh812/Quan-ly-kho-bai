import axiosClient from "./axiosClient";

const userApi = {
    login(username, password) {
        const url = '/auth/login/';
        return axiosClient
            .post(url, {
                username,
                password,
            })
            .then(response => {
                console.log(response);
                if (response.jwt) {
                    localStorage.setItem("token", response.jwt);
                    localStorage.setItem("user", JSON.stringify(response));
                }
                return response;
            });
    },
    logout(data) {
        const url = '/user/logout';
        return axiosClient.get(url);
    },
    listUser() {
        const url = '/auth/accounts';
        return axiosClient.get(url);
    },
    banAccount(id) {
        const url = '/questions/' + id + '/unapprove/';
        return axiosClient.post(url);
    },
    unBanAccount(id) {
        const url = '/auth/accounts/' + id ;
        return axiosClient.delete(url);
    },
    getProfile() {
        const user = JSON.parse(localStorage.getItem("user"));
        const url = '/profiles/'+ user.id;
        return axiosClient.get(url);
    },
    searchUser(email) {
        console.log(email);
        const params = {
            email: email.target.value
        }
        const url = '/user/searchByEmail';
        return axiosClient.get(url, { params });
    },
}

export default userApi;