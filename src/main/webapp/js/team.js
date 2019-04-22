$.get("getTeam",{dataType:"application/json"})
                  .done(function (data) {
                      alert(data)
                  })
                  .error()
                    .always()