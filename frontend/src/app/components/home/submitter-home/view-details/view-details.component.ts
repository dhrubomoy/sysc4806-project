import { Component, OnInit, Input, TemplateRef } from '@angular/core';
import { ViewCell } from 'ng2-smart-table';
import { NbDialogService } from '@nebular/theme';

@Component({
  selector: 'submitter-view-details',
  templateUrl: './view-details.component.html',
  styleUrls: ['./view-details.component.scss']
})
export class ViewDetailsComponent implements ViewCell, OnInit {
  
  @Input() value: any;
  @Input() rowData: any;

  constructor(private dialogService: NbDialogService) {}

  ngOnInit() {}

  open(dialog: TemplateRef<any>) {
    this.dialogService.open(dialog, { context: this.value });
  }
}