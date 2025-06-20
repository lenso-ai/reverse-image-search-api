import base64
import requests

ACCESS_TOKEN = "your_access_token"
IMAGE_PATH = "path_to_image"

def main():
    with open(IMAGE_PATH, "rb") as image_file:
        image_bytes = image_file.read()
        base64_image = base64.b64encode(image_bytes).decode('utf-8')

    json_body = {
        "image": base64_image,
        "category": "duplicates",
        "sortType": "QUALITY_DESCENDING",
        "page": 1
    }

    headers = {
        "Authorization": f"Bearer {ACCESS_TOKEN}",
        "Content-Type": "application/json"
    }

    response = requests.post("https://api.lenso.ai/search", json=json_body, headers=headers)

    print(f"HTTP Status Code: {response.status_code}")
    print("Response from API:")
    print(response.text)

if __name__ == "__main__":
    main()
