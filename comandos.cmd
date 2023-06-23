@echo off
docker ps -a --format "table {{.ID}}\t{{.Names}}\t{{.Image}}\t{{.Posts}}" | Select-String -NotMatch "_kube-system"
