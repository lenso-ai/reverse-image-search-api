# 📸 [Lenso.ai](https://lenso.ai) Reverse Image Search API examples

This repository provides practical usage examples for the **[Lenso.ai](https://lenso.ai) public API** – a platform for advanced image similarity search based on facial recognition or category-based filtering.

---

## 📌 Table of Contents

- 🔍 Overview
- 🛡️ Authorization
- 📡 API Endpoints
  - 👤 Face Search
  - 📂 Category Search
- ✅ Sample Response
- ❗ Error Handling

---

## 🔍 Overview

This project includes:

- Usage examples of the Lenso.ai API  
- JSON request/response formats  
- Authorization headers  
- Supported parameters and error responses  

The API allows developers to send images in base64 format and receive search results based on face similarity or predefined categories such as duplicates or landmarks.

---
## 🛡️ Authorization
Replace <your_access_token> with your actual access token obtained from lenso.ai website.
| Header | Type |Description |
| --- | --- |--- |
| `Authorization` | `string` | `Bearer token required for authentication. Format: Bearer <your_access_token>.` |

---
## 📡 API Endpoints

### 👤 Face Search

**Endpoint:**

```http
POST https://api.eyematch.ai/search
```
**Request parameters**
```json
{
    "image": "base64-encoded string",
    "sortType": "string",
    "domain": "string",
    "page": "integer (starts from 1)"
}
```

**Request Parameters Explanation**
| Parameter | Type |Description |
| --- | --- |--- |
| `image` | `string` | `A base64-encoded string of the image you want to search for` |
| `sortType` | `string` | `The sorting method for search results. Valid sort types are: "QUALITY_DESCENDING","QUALITY_ASCENDING","DATE_DESCENDING","DATE_ASCENDING"` |
| `domain` | `string` | `An optional field specifying the domain of the search` |
| `page` | `integer` | `The page number for search results. One page have a limit up to 20 results` |


### 📂 Category Search

**Endpoint:**

```http
POST https://api.lenso.ai/search
```
**Request parameters**
```json
{
    "image": "base64-encoded string",
    "sortType": "string",
    "domain": "string",
    "category": "string",
    "page": "integer (starts from 1)"
}
```

**Request Parameters Explanation**
| Parameter | Type |Description |
| --- | --- |--- |
| `image` | `string` | `A base64-encoded string of the image you want to search for` |
| `sortType` | `string` | `The sorting method for search results. Valid sort types are: "QUALITY_DESCENDING","QUALITY_ASCENDING","DATE_DESCENDING","DATE_ASCENDING"` |
| `domain` | `string` | `An optional field specifying the domain of the search` |
| `category` | `string` | `The category to filter search results. Valid categories are: "duplicates","landmarks","similar","related"` |
| `page` | `integer` | `The page number for search results. One page have a limit up to 20 results` |

---

## ✅ Sample Response
```json
{
    "results": [
        {
            "urlList": [
                {
                    "imageUrl": "https://example.com/image1.jpg",
                    "sourceUrl": "https://example.com/source1",
                    "title": "Example Image 1"
                }
            ],
            "base64Image": "iVBORw0KGgoAAAANSUhEUg...",
            "confidenceScore": (0-100)
        }
    ],
    "availablePages": 5
}
```
| Field                          | Type              | Description                              |
|-------------------------------|-------------------|------------------------------------------|
| results                       | Array             | List of search results                   |
| results[].urlList             | Array             | List of objects containing image data   |
| results[].urlList[].imageUrl  | String (URL)      | URL of the image                         |
| results[].urlList[].sourceUrl | String (URL)      | URL of the source page where the image is hosted |
| results[].urlList[].title     | String            | Title of the image                       |
| results[].base64Image         | String (base64)   | Image encoded in base64 format           |
| results[].confidenceScore     | Number (0-100)    | Confidence score of the result           |
| availablePages                | Number            | Number of available pages with results   |
---
## ❗ Error Handling
| Error Code | Message                                                                                     |
|------------|---------------------------------------------------------------------------------------------|
| 400        | Request cannot be null or empty.                                                            |
| 400        | Image cannot be null or blank.                                                              |
| 400        | Image must be encoded in Base64 format.                                                     |
| 400        | Category cannot be null or blank.                                                           |
| 400        | Invalid sort type. Valid sort types are "QUALITY_DESCENDING", "QUALITY_ASCENDING", "DATE_DESCENDING", "DATE_ASCENDING". |
| 400        | Sort type cannot be null or blank.                                                          |
| 400        | Invalid category name. Valid categories are "duplicates", "landmarks", "similar", "related".|
| 400        | Page number cannot be null.                                                                 |
| 400        | Invalid page number.                                                                        |
| 400        | API access request limit reached.                                                           |
| 401        | Unable to authorize. Invalid access token.                                                  |
| 401        | API access expired.                                                                         |
| 404        | Requested resource not found.                                                               |
| 429        | You have exceeded the allowed number of requests. Please try again later.                   |

