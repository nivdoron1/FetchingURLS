# README.md

## Web Crawler

A simple web crawler application that retrieves and saves HTML pages from a given URL, based on user-specified parameters such as depth, maximum number of URLs to process, and cross-level uniqueness.

### Classes

1. `Source` - Represents the source URL and parameters for the web crawling process.
2. `UrlExtracor` - Extracts URLs from an HTML document and returns a list of `HtmlBuilder` objects.
3. `HtmlBuilder` - Fetches and saves the HTML content of a URL and provides utility methods for working with URLs.
4. `UrlAdder` - Manages the web crawling process, including depth traversal and URL filtering based on the `Source` object.
5. `ExecuteHtml` - The main class that prompts the user for input and initializes the web crawling process.

### Usage

1. Compile and run the `ExecuteHtml` class.
2. Enter the following information when prompted:
    - The URL of the website to crawl.
    - The maximum number of URLs to execute at each level.
    - The depth factor for URL crawling.
    - Cross-level uniqueness (true or false).

After the crawling process is complete, the HTML files will be saved in directories corresponding to their depth levels.

### Example

```
Enter your URL website:
https://example.com

Enter your max amount of URL's that you want to execute each level:
5

Enter the depth factor:
2

Enter uniqueness write (true/false):
true
```

This will crawl the https://example.com website with a depth factor of 2, a maximum of 5 URLs per level, and cross-level uniqueness enabled. The resulting HTML files will be saved in directories named "0", "1", and "2", corresponding to their depth levels.