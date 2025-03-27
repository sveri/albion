import { HttpClient } from '@angular/common/http';
import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-item-crafting',
  imports: [],
  templateUrl: './item-crafting.component.html',
  styleUrl: './item-crafting.component.css'
})
export class ItemCraftingComponent implements OnInit {

  httpClient = inject(HttpClient);

  itemId: string | null = "";

  itemIdWithoutQuality: string | null = "";

  itemQuality: string | null = "";

  itemRecipes: any = [];

  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.itemId = this.route.snapshot.paramMap.get('item');

    this.parseItemId();

    this.fetchData();
  }

  parseItemId() {
    if (!this.itemId) {
      return;
    }

    const match = this.itemId.match(/^(.+?)@(.*)$/);
    if (match) {
      this.itemIdWithoutQuality = match[1];
      this.itemQuality = match[2];
    } else {
      this.itemIdWithoutQuality = this.itemId;
    }
  }

  fetchData() {
    this.httpClient.get('/api/itemrecipes').subscribe((data: any) => {
      this.itemRecipes = data;
      console.log(this.itemRecipes);
    });
  }

}
