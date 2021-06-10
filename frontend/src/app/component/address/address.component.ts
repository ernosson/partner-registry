import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {FileService} from "../../service/file.service";
import {FormBuilder} from "@angular/forms";
import {AddressService} from "../../service/address.service";
import {Address} from "../../model/address";
// @ts-ignore
import * as fileSaver from 'file-saver';

@Component({
  selector: 'app-address',
  templateUrl: './address.component.html',
  styleUrls: ['./address.component.scss']
})
export class AddressComponent implements OnInit {

  addresses: Address[];
  error: any;
  searchForm = this.formBuilder.group({
    searchField: '',
  });

  constructor(private router: Router, private addressService: AddressService, private fileService: FileService, private formBuilder: FormBuilder) {
  }

  getAddresses(path: string): void {
    this.addressService
      .getAddresses(path)
      .subscribe(
        addresses => (this.addresses = addresses),
        error => (this.error = error)
      )
  }

  deleteAddress(address: Address): void {
    this.addressService.delete(address).subscribe(res => {
      this.addresses = this.addresses.filter(h => h !== address);
    }, error => (this.error = error));
  }

  ngOnInit(): void {
    this.getAddresses("");
  }

  editAddress(): void {
    console.log("not ye implemented");
  }

  download(): void {
    this.fileService.downloadFile("address").subscribe((response: any) => {
      let blob: any = new Blob([response], {type: 'text/json; charset=utf-8'});
      fileSaver.saveAs(blob, 'addresses.csv')
    }), (error: any) => console.log('Error downloading the file'),
      () => console.info('File downloaded successfully');
  }

  filter(): void {
    let searchValueToSend;
    let input = this.searchForm.get('searchField').value;

    if (input) {
      searchValueToSend = input;
    } else {
      searchValueToSend = '';
    }
    this.getAddresses(searchValueToSend);
  }

}
