from fastapi import FastAPI, HTTPException
import httpx

app = FastAPI()



@app.get("/exchange-rate/{from_curr}/{to_curr}")
async def get_exchange_rate(from_curr: str, to_curr: str):
    url = f"https://v6.exchangerate-api.com/v6/76bbd4fd2f0355465749613c/pair/{from_curr}/{to_curr}"
    async with httpx.AsyncClient() as client:
        response = await client.get(url)
    
    if response.status_code != 200:
        raise HTTPException(status_code=500, detail="Failed to fetch exchange rate")
    
    return response.json()
