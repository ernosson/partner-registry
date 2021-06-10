import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {PartnerService} from "../../service/partner.service";
import {Partner} from "../../model/partner";
import {FileService} from "../../service/file.service";
import {FormBuilder} from '@angular/forms';
// @ts-ignore
import * as fileSaver from 'file-saver';

@Component({
  selector: 'app-partner',
  templateUrl: './partner.component.html',
  styleUrls: ['./partner.component.scss']
})
export class PartnerComponent implements OnInit {

  partners: Partner[];
  error: any;
  searchForm = this.formBuilder.group({
    searchField: '',
  });

  constructor(private router: Router, private partnerService: PartnerService, private fileService: FileService, private formBuilder: FormBuilder) {
  }

  getPartners(path: string): void {
    this.partnerService
      .getPartners(path)
      .subscribe(
        partners => (this.partners = partners),
        error => (this.error = error)
      )
  }

  deletePartner(partner: Partner): void {
    this.partnerService.delete(partner).subscribe(res => {
      this.partners = this.partners.filter(h => h !== partner);
    }, error => (this.error = error));
  }

  ngOnInit(): void {
    this.getPartners("");
  }

  editPartner(): void {
    console.log("not ye implemented");
  }

  download(): void {
    this.fileService.downloadFile("partner").subscribe((response: any) => {
      let blob: any = new Blob([response], {type: 'text/json; charset=utf-8'});
      fileSaver.saveAs(blob, 'partners.csv')
    }), (error: any) => console.log('Error downloading the file'),
      () => console.info('File downloaded successfully');
  }

  filter(): void {
    let searchValueToSend;
    let input = this.searchForm.get('searchField').value;

    if ( input ) {
      searchValueToSend = input;
    } else {
      searchValueToSend = '';
    }
    this.getPartners(searchValueToSend);
  }

}
