import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { ShipmentService } from 'src/app/service/shipment.service';

@Component({
  selector: 'app-shipment-list',
  templateUrl: './shipment-list.component.html',
  styleUrls: []
})
export class ShipmentListComponent implements OnInit {

  @ViewChild(MatTable) tablaArticulos!: MatTable<Storage>;
  @ViewChild(MatPaginator, { static: true }) paginador!: MatPaginator;

  dataSource: any;

  datos: Storage[] = [];

  columnas: string[] = ['number', 'vehicle', 'productName', 'shipmentDate', 'deliveryDate', 'storage' , 'amount', 'amountDesc','state', 'eliminar'];

  constructor(public shipmentService: ShipmentService) {}

  ngOnInit(): void {
    this.getShipments();
  }

  private getShipments() {
    this.shipmentService.getAllShipments().subscribe(
      (response) => {
        let result = response;
        if (result) {
          this.datos = result;
          this.dataSource = new MatTableDataSource<Storage>(this.datos);
          this.dataSource.paginator = this.paginador;
        } else {
          console.log('error');
        }
      },
      (err) => {
        console.log(err);
      }
    );
  }

  deleteShipment(idShipment: number) {
    if (confirm('¿Realmente quiere eliminar este envío?')) {
      this.shipmentService.deleteProduct(idShipment).subscribe(
        (response) => {
          let result = response;
          this.getShipments();
        },
        (err) => {
          console.log(err);
        }
      );
    }
  }

  filtrar(event: Event) {
    const filtro = (event.target as HTMLInputElement).value;
    console.log(filtro);

    this.dataSource.filter = filtro.trim().toLowerCase();
    this.dataSource.paginator = this.paginador;
  }

}
