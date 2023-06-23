@echo off
docker ps -a --format "table {{.ID}}\t{{.Names}}" | Select-String -NotMatch "_kube-system"
