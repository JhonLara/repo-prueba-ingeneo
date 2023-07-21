import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Product } from 'src/app/model/product';
import { Storage } from 'src/app/model/storage';
import { ShipmentService } from 'src/app/service/shipment.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-shipment',
  templateUrl: './shipment.component.html',
  styleUrls: [],
})
export class ShipmentComponent implements OnInit {
  submitted = false;

  calculateAmount: number = 0;

  calculateAmountDesc: number = 0;

  selectProduct!: Product;

  selectStorage!: Storage;

  productList: Product[] = [];

  storageList: Storage[] = [];

  constructor(
    public shipmentService: ShipmentService,
    public fb: FormBuilder,
    public toastr: ToastrService,
    private router: Router
  ) {}

  onReset() {
    this.submitted = false;
    this.shipmentService.formData.reset();
  }
  storage() {
    this.shipmentService.formData.value.storage = this.selectStorage;
    this.shipmentService.formData.value.amount =
      this.selectProduct.amount * this.shipmentService.formData.value.quantity;
    this.calculateAmount = this.shipmentService.formData.value.amount;
    if (this.shipmentService.formData.value.quantity > 10) {
      if (this.selectProduct.productType === 'TERESTRE') {
        this.calculateAmountDesc = this.calculateAmount * 0.95;
      } else {
        this.calculateAmountDesc = this.calculateAmount * 0.97;
      }
    } else {
      this.calculateAmountDesc = this.calculateAmount;
    }
  }
  ngOnInit() {
    this.infoForm();
    this.getProducts();
    this.getStorages();
  }

  private getProducts() {
    this.shipmentService.getAllProducts().subscribe(
      (response) => {
        let result = response;
        if (result) {
          this.productList = result;
        } else {
          console.log('error');
        }
      },
      (err) => {
        console.log(err);
      }
    );
  }

  private getStorages() {
    this.shipmentService.getAllStorages().subscribe(
      (response) => {
        let result = response;
        if (result) {
          this.storageList = result;
        } else {
          console.log('error');
        }
      },
      (err) => {
        console.log(err);
      }
    );
  }

  infoForm() {
    this.shipmentService.formData = this.fb.group({
      shipmentId: null,
      number: ['', [Validators.required, Validators.minLength(5)]],
      storage: ['', [Validators.required, Validators.minLength(8)]],
      quantity: ['', [Validators.required, Validators.minLength(8)]],
      vehicle: ['', [Validators.required, Validators.minLength(5)]],
      deliveryDate: ['', [Validators.required, Validators.minLength(8)]],
      state: ['', [Validators.required, Validators.minLength(5)]],
      productName: ['', [Validators.required, Validators.minLength(8)]],
      amount: ['', [Validators.required, Validators.minLength(5)]],
      amountDesc: ['', [Validators.required, Validators.minLength(8)]],
    });
  }
  onSubmit() {
    this.submitted = true;
    const val = this.shipmentService.formData.value;
  }

  addData() {
    console.log(this.shipmentService.formData.value);

    const shipment = this.shipmentService.formData.value;
    this.shipmentService.createShipment(shipment).subscribe((data) => {
      Swal.fire({
        position: 'top-end',
        icon: 'success',
        title: 'Alamcenamiento registrado',
        showConfirmButton: false,
        timer: 1500,
      });
      this.router.navigate(['/shipment-list']);
    });
  }
}
