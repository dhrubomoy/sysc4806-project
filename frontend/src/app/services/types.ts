import { CONSTANTS } from '../utils/constants';

export class Article {
    id?: number;
    title: string;
    text: string;
    reviewStatus?: string;
    submitter?: Submitter;
    reviewer?: Reviewer;
    reviewDeadline?: string;
}

export class User {
    id?: number;
    name?: string;
    username?: string;
    role?: string;
}

export class Submitter extends User {
    constructor() {
        super();
        this.role = CONSTANTS.ROLES.SUBMITTER;
    }
}

export class Reviewer extends User {
    constructor() {
        super();
        this.role = CONSTANTS.ROLES.REVIEWER;
    }
}

export class ReviewerInfo {
    reviewerId: number;
    reviewDeadline: string;

    constructor(reviewerId: number, reviewDeadline: string) {
        this.reviewerId = reviewerId;
        this.reviewDeadline = reviewDeadline;
    }
  }