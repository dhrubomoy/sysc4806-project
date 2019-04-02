

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

    static getMonthNumberToMonthName(num: number): string {
        let monthNames = [
            "January", "February", "March",
            "April", "May", "June",
            "July", "August", "September",
            "October", "November", "December"
        ];
        return monthNames[num-1].substr(0, 3);
    }

    static convertDateToAnotherFormat(dateString: string, from: string, to: string): string {
        let splitString = [];
        let resultString = '';
        switch(from) {
            case 'mm-dd-yyyy':
                // e.g. "03-26-2019 12:00:00"
                splitString = dateString.split(/(\d{2})-(\d{2})-(\d{4})/g);
                break;
            default:
                throw new Error(from + ' format not found'); 
        }
        switch(to) {
            // e.g. Sep 16, 2018
            case 'Mon dd, yyyy':
                resultString = ArticleUtil.getMonthNumberToMonthName(parseInt(splitString[2])) + ' ' + splitString[1] + ', ' + splitString[3];
                break;
            default:
                throw new Error(to + ' format not found'); 
        }
        return resultString;

    }

    static getDateInFormat(date: Date, format: String): string {
        let result: string = '';
        switch(format) {
            case 'dd/mm/yyyy':
                result = date.getDate()+'/'+ (date.getMonth()+1) +'/'
                + date.getFullYear();
                break;
            default:
                result = 'Date format not found';
        }
        return result;
    }
}