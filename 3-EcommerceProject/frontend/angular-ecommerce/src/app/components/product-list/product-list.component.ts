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
  currentCategoryId: number;
  currentCategoryName: string;
  searchMode: boolean;

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
    this.searchMode = this.route.snapshot.paramMap.has("keyword");
    if (this.searchMode) {
      this.handleSearchProducts();
    } else {
      this.handleListProducts();
    }
  }
  handleSearchProducts() {
    const theKeyWord: string = this.route.snapshot.paramMap.get("keyword");

    // now search for product using those keywords
    this.productService.searchProduct(theKeyWord).subscribe(data => {
      this.products = data;
    });
  }

  handleListProducts() {
    const hasCategoryId: boolean = this.route.snapshot.paramMap.has("id");

    if (hasCategoryId) {
      // get the "id" param string. Convert it to a number using the "+" symbol
      this.currentCategoryId = +this.route.snapshot.paramMap.get("id");
      this.currentCategoryName = this.route.snapshot.paramMap.get("name");
    } else {
      // no category id available ... default category to 1
      this.currentCategoryId = 1;
      this.currentCategoryName = "Books";
    }

    // now get the products for the given category id
    this.productService
      .getProductList(this.currentCategoryId)
      .subscribe(data => {
        this.products = data;
      });
  }
}
