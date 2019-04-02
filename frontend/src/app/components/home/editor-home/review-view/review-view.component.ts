import { 
  Component, OnInit, Input, 
  Output, EventEmitter, TemplateRef 
} from '@angular/core';
import { ViewCell } from 'ng2-smart-table';
import { NbDialogService, NbDialogRef } from '@nebular/theme';
import { ArticleService } from 'src/app/services/article.service';

@Component({
  selector: 'button-view',
  templateUrl: './review-view.component.html',
  styleUrls: ['./review-view.component.scss']
})
export class ReviewViewComponent implements ViewCell, OnInit {
  
  @Input() value: any;
  @Input() rowData: any;

  reviewStatusOptions = [
    { value: 'accepted', label: 'Accept' },
    { value: 'rejected', label: 'Reject' },
  ];
  finalReviewStatus: string;

  @Output() updateTable: EventEmitter<any> = new EventEmitter();

  constructor(private dialogService: NbDialogService, private articleService: ArticleService) {}

  ngOnInit() {
    this.finalReviewStatus = 'accepted';
  }

  open(dialog: TemplateRef<any>) {
    this.dialogService.open(dialog, { context: this.value });
  }

  update(dialogRef: NbDialogRef<any>) {
    this.updateTable.emit(this.rowData);
    console.log(this);
    this.articleService.acceptOrRejectArticle(this.finalReviewStatus, this.rowData.id).subscribe(
      data => {
        console.log(data);
        // Update the entire table in editor home page
        this.updateTable.emit(this.rowData);
        dialogRef.close();
      },
      error => {
        console.log(error);
      }
    );
  }
}