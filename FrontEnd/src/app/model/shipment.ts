import { DatePipe } from "@angular/common";

export interface Shipment {
    shipmentId: number;
    number: string;
    storage: Storage;
    vehicle: string;
    deliveryDate: Date;
    state: string;
    productName: string;
    amount: number;
    amountDesc: number;
}