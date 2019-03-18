import { environment } from '../../environments/environment';

let URL = environment.APIEndpoint;

export const CONSTANTS = {

    AUTH_URL: {
        SIGN_IN: URL+"/api/auth/signin",
        SIGN_UP: URL+"/api/auth/signup",
    },

    USER_URL: {
        SUBMITTER: URL+"/api/submitter",
        EDITOR: URL+"/api/editor",
        REVIEWER: URL+"/api/reviewer", 
        REVIEWER_LIST: URL+"/api/reviewerList",
    },

    ARTICLE_URL: {
        SUBMITTER_CREATE_ARTICLE: URL+"/api/submitter/articles/create",
        SUBMITTER_LIST_ARTICLE: URL+"/api/submitter/articles",
        ALL_ARTICLE: URL+"/api/articles",
        SET_REVIEWER_FOR_ARTICLE: URL+"/api/articles/{id}/setReviewInfo",
    },

    ROLES: {
        SUBMITTER: "ROLE_SUBMITTER",
        EDITOR: "ROLE_EDITOR",
        REVIEWER: "ROLE_REVIEWER"
    }
}