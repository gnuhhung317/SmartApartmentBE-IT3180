@echo off

docker login

echo Bo may dang cap nhat moi nhat...
docker-compose pull

echo bo may bat dau build...
docker-compose up -d

echo Build ket thuc...