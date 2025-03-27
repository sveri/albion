import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-item-crafting',
  imports: [],
  templateUrl: './item-crafting.component.html',
  styleUrl: './item-crafting.component.css'
})
export class ItemCraftingComponent implements OnInit {

  itemId: string | null = "";

  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.itemId = this.route.snapshot.paramMap.get('item');
    console.log(this.itemId);
  }

}
