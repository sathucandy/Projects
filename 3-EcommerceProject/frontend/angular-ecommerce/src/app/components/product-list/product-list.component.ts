import { Component, OnInit } from "@angular/core";
import { ProductService } from "src/app/services/product.service";
import { Product } from "src/app/common/product";
import { ActivatedRoute } from "@angular/router";

@Component({
  selector: "app-product-list",
  // templateUrl: './product-list-table.component.html',
  templateUrl: "./product-list.component.html",
  styleUrls: ["./product-list.component.css"]
})
export class ProductListComponent implements OnInit {
  products: Product[];
  currentCategortId: number;

  constructor(
    private productService: ProductService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.route.paramMap.subscribe(() => {
      this.listProducts();
    });
  }

  listProducts() {
    // check if id param is available or not
    const hasCategoryId: boolean = this.route.snapshot.paramMap.has("id");

    if (hasCategoryId) {
      // get the "id" param string. Convert it to a number using the "+" symbol
      this.currentCategortId = +this.route.snapshot.paramMap.get("id");
    }else{
      // no category id available ... default category to 1
      this.currentCategortId = 1;
    }

    // now get the products for the given category id
    this.productService.getProductList(this.currentCategortId).subscribe(data => {
      this.products = data;
    });
  }
}
