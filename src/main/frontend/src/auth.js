class Auth {
    constructor() {
        this.loggedIn = false;
    }

    login(cb) {
        this.loggedIn = true;
        cb();
    }

    loggout(cb) {
        this.loggedIn = false;
        cb();
    }

    isLoggedIn() {
        return this.loggedIn;
    }
}

export default new Auth();
