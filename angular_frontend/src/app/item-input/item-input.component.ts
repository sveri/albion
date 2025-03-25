import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, inject, OnInit } from '@angular/core';

@Component({
  selector: 'app-item-input',
  standalone: true,
  imports: [],
  templateUrl: './item-input.component.html',
  styleUrl: './item-input.component.css'
})
export class ItemInputComponent implements OnInit {

  httpClient = inject(HttpClient);

  itemData: any = [];

  ngOnInit(): void {
    this.fetchData();
  }

  fetchData() {
    this.httpClient.get('/api/items').subscribe((data) => {
      console.log(data);
      this.itemData = data;
    });
  }

  filterResults(text: string) {
    console.log("clicked");
    if (!text) {
      // this.filteredLocationList = this.housingLocationList;
      return;
    }

    // this.filteredLocationList = this.housingLocationList.filter(
    //   housingLocation => housingLocation?.city.toLowerCase().includes(text.toLowerCase())
    // );
  }

}
