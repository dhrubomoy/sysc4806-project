

export class ArticleUtil {

    static getStatusFilter(articles: any[]) {
        let statuses = articles.map(a => a.status);
        let uniqueStatuses = statuses.filter((v, i, a) => a.indexOf(v) === i);
        let statusList = uniqueStatuses.map(s => {
            return { value: s, title: s }
        });
        return {
            type: 'list',
            config: {
            selectText: 'Select...',
            list: statusList,
            },
        };
    }

    static getReviewStatus(status): string {
        let result = 'Not Set';
        switch(status) {
            case 'SUBMITTED':
            result = 'Submitted';
            break;
        case 'IN_REVIEW':
            result = 'In Review';
            break;
        case 'ACCEPTED':
            result = 'Accepted';
            break;
        case 'REJECTED':
            result = 'Rejected';
            break;
        }
        return result;
    }
}