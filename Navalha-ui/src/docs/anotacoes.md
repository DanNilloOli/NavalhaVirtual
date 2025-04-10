Para não criar outra pasta na hora de criar o projeto basta escrever `--diretory=./`
Ex.:  ng n barber-shop-ui --directory=./

### Components
São as partes visuais, para criar um component
ng g c clients/new-client &&

### Services
Servições que deseja utilizar em outro lugar

`touch` criar, ele utilizamos pra indicar onde vamos criar

# Bibliotecas
### Para mascarar os campos (telefone, cpf, etc)

`import { provideNgxMask } from 'ngx-mask';`
`provideNgxMask({})`


### Delete não tem paramentro então ao invés de:
  return this.http.post<SaveClientResponse>(`${this.basePath}clients`, request)

Seria:
    return this.http.delete<void>(`${this.basePath}clients${id}`)


### Curiosidade
pode usar _ para ignorar (mesmo efeito de utilizar parenteses)
    this.httpService.save(request).subscribe(_ => {}) ou
    this.httpService.save(request).subscribe(() => {})
    

### Rotas
Em Angular Router, os curingas (* e **) são usados para capturar rotas não definidas.

* isolado não tem dignificado especial no Angular
** Captura qualquer rota que não tenha sido definida. Conhecido como <strong>`wildcard`</strong>
